package vn.edu.hcmus.worker_service.message;

public class WorkerRegisterMessage
{
    private String serviceName;
    private String hostAddress;
    private int port;

    public WorkerRegisterMessage(final String serviceName, final String hostAddress, final int port)
    {
        this.serviceName = serviceName;
        this.hostAddress = hostAddress;
        this.port = port;
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
}
