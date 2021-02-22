package vn.edu.hcmus.master_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Calendar;

@Entity
@Table(name = "workers")
@IdClass(WorkerId.class)
public class Worker
{
    @Id
    @Column(name = "service_name")
    private String serviceName;

    @Id
    @Column(name = "host_address")
    private String hostAddress;

    @Id
    private int port;

    private String status;

    private int capacity;

    @Column(name = "current_processing")
    private int currentProcessing;

    @Column(name = "current_available")
    private int currentAvailable;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdTime;

    @Column(name = "last_updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdatedTime;

    @Column(name = "last_success_updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastSuccessUpdatedTime;

    public Worker()
    {}

    public Worker(final String serviceName, final String hostAddress, final int port, final String status, final int capacity, final int currentProcessing, final int currentAvailable)
    {
        this.serviceName = serviceName;
        this.hostAddress = hostAddress;
        this.port = port;
        this.status = status;
        this.capacity = capacity;
        this.currentProcessing = currentProcessing;
        this.currentAvailable = currentAvailable;
        this.createdTime =  Calendar.getInstance();
        this.lastUpdatedTime =  Calendar.getInstance();
        this.lastSuccessUpdatedTime =  Calendar.getInstance();
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(final String status)
    {
        this.status = status;
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

    public Calendar getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(final Calendar createdTime)
    {
        this.createdTime = createdTime;
    }

    public Calendar getLastUpdatedTime()
    {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(final Calendar lastUpdatedTime)
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Calendar getLastSuccessUpdatedTime()
    {
        return lastSuccessUpdatedTime;
    }

    public void setLastSuccessUpdatedTime(final Calendar lastSuccessUpdatedTime)
    {
        this.lastSuccessUpdatedTime = lastSuccessUpdatedTime;
    }

    @Override
    public String toString()
    {
        return String.format("Host: %s, port: %s, name: %s", getHostAddress(), getPort(), getServiceName());
    }
}
