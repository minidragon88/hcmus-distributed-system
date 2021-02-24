package vn.edu.hcmus.master_service.model;

import vn.edu.hcmus.commons.message.WorkMessage;
import vn.edu.hcmus.commons.message.WorkStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "works")
public class Work
{
    @Id
    private String id;
    @Column(name = "left_operand")
    private int left;
    @Column(name = "right_operand")
    private int right;
    private String operator;
    private String status;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdTime;

    @Column(name = "last_updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdatedTime;

    @Column(name = "last_success_updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastSuccessUpdatedTime;

    public Work()
    {}

    public Work(final String id, final int left, final int right, final String operator, final String status)
    {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        }
        else {
            this.id = id;
        }
        this.left = left;
        this.right = right;
        this.operator = operator;
        if (status == null) {
            this.status = WorkStatus.QUEUE.name();
        }
        else {
            this.status = status;
        }
        this.createdTime =  Calendar.getInstance();
        this.lastUpdatedTime =  Calendar.getInstance();
        this.lastSuccessUpdatedTime =  Calendar.getInstance();
    }

    public static Work fromBody(final WorkMessage message)
    {
        return new Work(null, message.getLeft(), message.getRight(), message.getOperator().name(), message.getStatus().name());
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public int getLeft()
    {
        return left;
    }

    public void setLeft(final int left)
    {
        this.left = left;
    }

    public int getRight()
    {
        return right;
    }

    public void setRight(final int right)
    {
        this.right = right;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(final String operator)
    {
        this.operator = operator;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(final String status)
    {
        this.status = status;
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
        return String.format("id: %s, left: %s, right: %s, operator: %s", getId(), getLeft(), getRight(), getOperator());
    }
}
