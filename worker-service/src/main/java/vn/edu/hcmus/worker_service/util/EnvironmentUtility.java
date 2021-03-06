package vn.edu.hcmus.worker_service.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public final class EnvironmentUtility implements ApplicationContextAware
{
    private static ApplicationContext context;

    private EnvironmentUtility() {}

    public static String getServerAddress()
    {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (final UnknownHostException e) {
            return null;
        }
    }

    public static int getServerPort()
    {
        return Integer.valueOf(getEnvironment().getProperty("server.port"));
    }

    public static String getMasterServerHost()
    {
        return getEnvironment().getProperty("master.host");
    }

    public static int getMasterServerPort()
    {
        return Integer.valueOf(getEnvironment().getProperty("master.port"));
    }

    public static int getCapacity()
    {
        final String capacity = getEnvironment().getProperty("server.capacity");
        if (capacity != null) {
            return Integer.valueOf(capacity);
        }
        return Constants.DEFAULT_CAPACITY;
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
