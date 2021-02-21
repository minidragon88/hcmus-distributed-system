package vn.edu.hcmus.master_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;
import vn.edu.hcmus.master_service.model.Worker;
import vn.edu.hcmus.master_service.model.WorkerId;
import vn.edu.hcmus.master_service.service.WorkerService;
import vn.edu.hcmus.master_service.util.Utilities;

import javax.persistence.EntityManager;

import java.util.Calendar;
import java.util.Map;
@RestController
@RequestMapping("/workers")
public class WorkersController
{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public String helloGradle()
    {
        return "Master!";
    }

    @PostMapping("/status")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<APIResponse<String>> register(@RequestHeader final Map<String, String> headers, @RequestBody final WorkerStatusMessage message)
    {
        final WorkerId id = Utilities.toWorkerId(message);
        Worker worker = workerService.findById(id);
        if (worker == null) {
            worker = Utilities.toWorker(message);
        }
        else {
            worker.setLastUpdatedTime(Calendar.getInstance());
        }
        entityManager.persist(worker);
        return new APIResponse<String>(HttpStatus.OK.value(), "Registered successfully", null).toResponseEntity();
    }
}
