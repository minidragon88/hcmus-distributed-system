package vn.edu.hcmus.worker_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import retrofit2.Call;
import retrofit2.Retrofit;
import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;
import vn.edu.hcmus.commons.utils.RetryExecutor;
import vn.edu.hcmus.commons.utils.Utilities;
import vn.edu.hcmus.worker_service.api.MasterApi;
import vn.edu.hcmus.worker_service.util.AddressUtility;

@Configuration
@EnableScheduling
public class ScheduledConfiguration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledConfiguration.class);

    @Scheduled(initialDelay = 1_000, fixedDelay = 10_000)
    public void updateStatus()
    {
        final WorkerStatusMessage message = new WorkerStatusMessage("worker", AddressUtility.getServerAddress(), AddressUtility.getServerPort(), 10, 0, 10);
        final Retrofit retrofit = Utilities.getRetrofit(String.format("http://%s:%s", AddressUtility.getMasterServerHost(), AddressUtility.getMasterServerPort()));
        final MasterApi api = retrofit.create(MasterApi.class);
        final Call<APIResponse<String>> request = api.updateStatus(message);
        try {
            new RetryExecutor.Builder<APIResponse<String>>().withCall(request).build().executeWithRetry();
        }
        catch (final Exception e) {
            LOGGER.warn("Failed to update status");
            LOGGER.warn(e.getMessage());
        }
    }
}
