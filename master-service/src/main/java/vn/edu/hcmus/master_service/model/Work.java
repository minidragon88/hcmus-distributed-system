package vn.edu.hcmus.master_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(value = Include.NON_NULL)
public class Work
{
    @Id
    private String id;
    @Column(name = "left_operand")
    private Integer left;
    @Column(name = "right_operand")
    private Integer right;
    private Integer result;
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

    public Work(final String id, final Integer left, final Integer right, final Integer result, final String operator, final String status)
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
        String operator = null;
        if (message.getOperator() != null) {
            operator = message.getOperator().name();
        }
        String status = null;
        if (message.getStatus() != null) {
            status = message.getStatus().name();
        }
        return new Work(null, message.getLeft(), message.getRight(), message.getResult(), operator, status);
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public Integer getLeft()
    {
        return left;
    }

    public void setLeft(final Integer left)
    {
        this.left = left;
    }

    public Integer getRight()
    {
        return right;
    }

    public void setRight(final Integer right)
    {
        this.right = right;
    }

    public Integer getResult()
    {
        return result;
    }

    public void setResult(final Integer result)
    {
        this.result = result;
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
