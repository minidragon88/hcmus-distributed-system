package vn.edu.hcmus.worker_service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.edu.hcmus.worker_service.api.MasterApi;
import vn.edu.hcmus.worker_service.message.APIResponse;
import vn.edu.hcmus.worker_service.message.WorkerRegisterMessage;
import vn.edu.hcmus.worker_service.util.AddressUtility;
import vn.edu.hcmus.worker_service.util.Utilities;

import java.io.IOException;

@Component
public class StartUpComponent implements ApplicationListener<ApplicationReadyEvent>
{
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event)
    {
        final WorkerRegisterMessage message = new WorkerRegisterMessage("worker", AddressUtility.getServerAddress(), AddressUtility.getServerPort());
        final Retrofit retrofit = Utilities.getRetrofit(String.format("http://%s:%s", AddressUtility.getMasterServerHost(), AddressUtility.getMasterServerPort()));
        final MasterApi api = retrofit.create(MasterApi.class);
        final Call<APIResponse<String>> request = api.register(message);
        Response<APIResponse<String>> response;
        try {
            response = request.execute();
            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            System.out.println(response.body());
            System.out.println("PPPPPPPPPPPPPPPPPPPPPPP");
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
