package vn.edu.hcmus.master_service.util;

import vn.edu.hcmus.commons.message.WorkerStatusMessage;
import vn.edu.hcmus.master_service.model.Worker;
import vn.edu.hcmus.master_service.model.WorkerId;

public final class Utilities
{
    private Utilities() {}

    public static Worker toWorker(final WorkerStatusMessage message)
    {
        return new Worker(message.getServiceName(), message.getHostAddress(), message.getPort(), message.getCapacity(), message.getCurrentProcessing(), message.getCurrentAvailable());
    }

    public static WorkerId toWorkerId(final WorkerStatusMessage message)
    {
        return new WorkerId(message.getServiceName(), message.getHostAddress(), message.getPort());
    }
}
