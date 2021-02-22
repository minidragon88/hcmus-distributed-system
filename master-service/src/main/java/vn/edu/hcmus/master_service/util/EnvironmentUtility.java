package vn.edu.hcmus.master_service.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public final class EnvironmentUtility implements ApplicationContextAware
{
    private static ApplicationContext context;

    private EnvironmentUtility() {}

    public static long getWaitForWorkerResponse()
    {
        final String waitForResponse = getEnvironment().getProperty("server.wait_for_response");
        if (waitForResponse != null) {
            return Integer.valueOf(waitForResponse);
        }
        return Constants.DEFAULT_WAIT_FOR_WORKER_IN_SECONDS * 1000;
    }

    private static Environment getEnvironment()
    {
        return context.getEnvironment();
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
    {
        context = applicationContext;
    }
}
