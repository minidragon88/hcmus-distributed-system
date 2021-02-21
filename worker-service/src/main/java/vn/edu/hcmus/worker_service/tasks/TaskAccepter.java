package vn.edu.hcmus.worker_service.tasks;

public interface TaskAccepter
{
    public void accept(String task, int capacity, int currentProcessing);
}
