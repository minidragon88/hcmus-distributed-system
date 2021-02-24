package vn.edu.hcmus.commons.message;

public class Work
{
    private String id;
    private int left;
    private int right;
    private Operator operator;

    public Work(final String id, final int left, final int right, final Operator operator)
    {
        this.id = id;
        this.left = left;
        this.right = right;
        this.operator = operator;
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
}
