package vn.edu.hcmus.master_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import retrofit2.Call;
import retrofit2.Retrofit;
import vn.edu.hcmus.commons.api.WorkerApi;
import vn.edu.hcmus.commons.message.Status;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;
import vn.edu.hcmus.commons.utils.RetryExecutor;
import vn.edu.hcmus.commons.utils.Utilities;
import vn.edu.hcmus.master_service.model.Worker;
import vn.edu.hcmus.master_service.service.WorkerService;
import vn.edu.hcmus.master_service.util.EnvironmentUtility;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Calendar;

@Configuration
@EnableScheduling
public class ServiceConfiguration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfiguration.class);
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WorkerService workerService;

    @Scheduled(initialDelay = 1_000, fixedDelay = 60_000)
    @Transactional
    public void refreshWorkerStatuses()
    {
        System.out.println("scheduled");
        for (final Worker worker : workerService.findAll()) {
            final Retrofit retrofit = Utilities.getRetrofit(String.format("http://%s:%s", worker.getHostAddress(), worker.getPort()));
            final WorkerApi workerApi = retrofit.create(WorkerApi.class);
            final Call<WorkerStatusMessage> request = workerApi.getStatus();
            try {
                final WorkerStatusMessage message = new RetryExecutor.Builder<WorkerStatusMessage>().withCall(request).withMaxRetry(2).build().executeWithRetry().body();
                worker.setStatus(message.getStatus().name().toLowerCase());
                worker.setCapacity(message.getCapacity());
                worker.setCurrentAvailable(message.getCurrentAvailable());
                worker.setCurrentProcessing(message.getCurrentProcessing());
                worker.setLastUpdatedTime(Calendar.getInstance());
                worker.setLastSuccessUpdatedTime(Calendar.getInstance());
                entityManager.persist(worker);
            }
            catch (final Exception e) {
                LOGGER.warn("Failed to get status from " + worker.toString());
                LOGGER.warn(e.getMessage());
                if (Calendar.getInstance().getTimeInMillis() - worker.getLastSuccessUpdatedTime().getTimeInMillis() > EnvironmentUtility.getWaitForWorkerResponse()) {
                    LOGGER.warn("Removing " + worker.toString());
                    entityManager.remove(worker);
                }
                else {
                    worker.setStatus(Status.BUSY.name());
                    worker.setLastUpdatedTime(Calendar.getInstance());
                    entityManager.persist(worker);
                }
            }
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup()
    {
        System.out.println("hello world, I have just started up");
    }
}
