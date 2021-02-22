package vn.edu.hcmus.worker_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;
import vn.edu.hcmus.worker_service.tasks.TasksQueue;

import java.io.IOException;

@RestController("/")
public class StatusController
{
    @Autowired
    private Environment env;
    @GetMapping(path = "/status")
    public ResponseEntity<WorkerStatusMessage> getStatus() throws IOException
    {
        return new ResponseEntity<WorkerStatusMessage>(TasksQueue.getTasksQueue().getStatusMessage(), HttpStatus.OK);
    }
}
