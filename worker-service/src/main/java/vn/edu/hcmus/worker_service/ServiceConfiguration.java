package vn.edu.hcmus.worker_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import retrofit2.Call;
import retrofit2.Retrofit;
import vn.edu.hcmus.commons.api.MasterApi;
import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.utils.RetryExecutor;
import vn.edu.hcmus.commons.utils.Utilities;
import vn.edu.hcmus.worker_service.tasks.TasksQueue;
import vn.edu.hcmus.worker_service.util.EnvironmentUtility;

@Configuration
@EnableScheduling
public class ServiceConfiguration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfiguration.class);

    @Scheduled(initialDelay = 1_000, fixedDelay = 10_000)
    public void updateStatus()
    {
        final Retrofit retrofit = Utilities.getRetrofit(String.format("http://%s:%s", EnvironmentUtility.getMasterServerHost(), EnvironmentUtility.getMasterServerPort()));
        final MasterApi api = retrofit.create(MasterApi.class);
        final Call<APIResponse<String>> request = api.updateStatus(TasksQueue.getTasksQueue().getStatusMessage());
        try {
            new RetryExecutor.Builder<APIResponse<String>>().withCall(request).build().executeWithRetry();
        }
        catch (final Exception e) {
            LOGGER.warn("Failed to update status");
            LOGGER.warn(e.getMessage());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup()
    {
        System.out.println("hello world, I have just started up");
    }
}
