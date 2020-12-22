package vn.edu.hcmus.master_service.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slaves")
public class WorkerNode
{
    @Id
    private String address;
    private String status;

    public WorkerNode()
    {}

    public WorkerNode(final String address, final String status)
    {
        this.address = address;
        this.status = status;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(final String address)
    {
        this.address = address;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(final String status)
    {
        this.status = status;
    }
}
