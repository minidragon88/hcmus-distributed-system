package vn.edu.hcmus.worker_service.tasks;

public class DefaultTaskAccepter implements TaskAccepter
{
    @Override
    public void accept(final String task, final int capacity, final int currentProcessing)
    {
       if (currentProcessing == capacity) {
           throw new RuntimeException("Maximum capacity reached, task rejected");
       }
    }
}
