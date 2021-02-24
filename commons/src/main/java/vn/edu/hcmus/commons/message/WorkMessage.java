package vn.edu.hcmus.commons.message;

public class WorkMessage
{
    private String id;
    private Integer left;
    private Integer right;
    private Integer result;
    private Operator operator;
    private WorkStatus status;

    public WorkMessage(final String id, final Integer left, final Integer right, final Integer result, final Operator operator, final WorkStatus status)
    {
        this.id = id;
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.status = status;
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

    public Operator getOperator()
    {
        return operator;
    }

    public void setOperator(final Operator operator)
    {
        this.operator = operator;
    }

    public WorkStatus getStatus()
    {
        return status;
    }

    public void setStatus(final WorkStatus status)
    {
        this.status = status;
    }
}
