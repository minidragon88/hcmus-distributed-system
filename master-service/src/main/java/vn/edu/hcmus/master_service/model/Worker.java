package vn.edu.hcmus.master_service.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "workers")
@IdClass(WorkerId.class)
public class Worker
{
    @Id
    @Column(name="service_name")
    private String serviceName;

    @Id
    @Column(name="host_address")
    private String hostAddress;

    @Id
    private int port;

    private int capacity;

    @Column(name="current_processing")
    private int currentProcessing;

    @Column(name="current_available")
    private int currentAvailable;

    @Column(name="created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdTime;

    @Column(name="last_updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdatedTime;

    public Worker()
    {}

    public Worker(final String serviceName, final String hostAddress, final int port, final int capacity, final int currentProcessing, final int currentAvailable)
    {
        this.serviceName = serviceName;
        this.hostAddress = hostAddress;
        this.port = port;
        this.capacity = capacity;
        this.currentProcessing = currentProcessing;
        this.currentAvailable = currentAvailable;
        this.createdTime =  Calendar.getInstance();
        this.lastUpdatedTime =  Calendar.getInstance();
    }
    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(final String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentProcessing() {
        return currentProcessing;
    }

    public void setCurrentProcessing(final int currentProcessing) {
        this.currentProcessing = currentProcessing;
    }

    public int getCurrentAvailable() {
        return currentAvailable;
    }

    public void setCurrentAvailable(final int currentAvailable) {
        this.currentAvailable = currentAvailable;
    }
}
