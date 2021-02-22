package vn.edu.hcmus.worker_service.tasks;

import vn.edu.hcmus.commons.message.Status;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;
import vn.edu.hcmus.worker_service.util.EnvironmentUtility;

import java.util.concurrent.ConcurrentLinkedQueue;

public final class TasksQueue
{
    private TasksQueue() {}

    private static final TasksQueue QUEUE = new TasksQueue();
    private final ConcurrentLinkedQueue<String> tasks = new ConcurrentLinkedQueue<String>();
    private TaskAccepter accepter = new DefaultTaskAccepter();

    public static TasksQueue getTasksQueue()
    {
        return QUEUE;
    }

    public int getAvailableCapacity()
    {
        return EnvironmentUtility.getCapacity() - tasks.size();
    }

    public int getCurrentProcessing()
    {
        return tasks.size();
    }

    public String poll()
    {
        return tasks.poll();
    }

    public void add(final String task)
    {
        accepter.accept(task, EnvironmentUtility.getCapacity(), getAvailableCapacity());
        tasks.add(task);
    }

    public void setAccepter(final TaskAccepter accepter)
    {
        this.accepter = accepter;
    }

    public WorkerStatusMessage getStatusMessage()
    {
        return new WorkerStatusMessage("worker", EnvironmentUtility.getServerAddress(), EnvironmentUtility.getServerPort(), Status.UP, getAvailableCapacity(), getCurrentProcessing(), getAvailableCapacity());
    }
}
