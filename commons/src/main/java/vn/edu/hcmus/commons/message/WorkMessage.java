package vn.edu.hcmus.commons.message;

public class WorkMessage
{
    private String id;
    private int left;
    private int right;
    private Operator operator;
    private WorkStatus status;

    public WorkMessage(final String id, final int left, final int right, final Operator operator, final WorkStatus status)
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
