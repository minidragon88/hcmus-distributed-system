package vn.edu.hcmus.worker_service.message;

public class WorkerStatusMessage
{
    private String serviceName;
    private String hostAddress;
    private int port;
    private int capacity;
    private int currentProcessing;
    private int currentAvailable;

    public WorkerStatusMessage(final String serviceName, final String hostAddress, final int port, final int capacity, final int currentProcessing, final int currentAvailable)
    {
        this.serviceName = serviceName;
        this.hostAddress = hostAddress;
        this.port = port;
        this.capacity = capacity;
        this.currentProcessing = currentProcessing;
        this.currentAvailable = currentAvailable;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(final String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getHostAddress()
    {
        return hostAddress;
    }

    public void setHostAddress(final String hostAddress)
    {
        this.hostAddress = hostAddress;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(final int port)
    {
        this.port = port;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(final int capacity)
    {
        this.capacity = capacity;
    }

    public int getCurrentProcessing()
    {
        return currentProcessing;
    }

    public void setCurrentProcessing(final int currentProcessing)
    {
        this.currentProcessing = currentProcessing;
    }

    public int getCurrentAvailable()
    {
        return currentAvailable;
    }

    public void setCurrentAvailable(final int currentAvailable)
    {
        this.currentAvailable = currentAvailable;
    }
}
