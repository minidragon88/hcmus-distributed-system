package vn.edu.hcmus.master_service.model;

import java.io.Serializable;

public class WorkerId implements Serializable
{
    private static final long serialVersionUID = 8361055844997491028L;
    private String serviceName;
    private String hostAddress;
    private int port;
    
    public WorkerId(final String serviceName, final String hostAddress, final int port)
    {
        this.setServiceName(serviceName);
        this.setHostAddress(hostAddress);
        this.setPort(port);
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
