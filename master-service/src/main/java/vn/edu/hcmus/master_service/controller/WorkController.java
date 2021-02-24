package vn.edu.hcmus.master_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.message.WorkMessage;
import vn.edu.hcmus.master_service.model.Work;
import vn.edu.hcmus.master_service.service.WorkService;

import javax.persistence.EntityManager;

import java.util.Map;

@RestController
@RequestMapping("/works")
public class WorkController
{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WorkService workService;

    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Work>> getWorkById(@PathVariable final String id)
    {
        final Work work = workService.findById(id);
        if (work == null) {
            return new APIResponse<Work>(HttpStatus.NOT_FOUND.value(), "Not found", null).toResponseEntity();
        }
        return new APIResponse<Work>(HttpStatus.OK.value(), "Found", work).toResponseEntity();
    }

    @PostMapping("/submit")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<APIResponse<Work>> submit(@RequestHeader final Map<String, String> headers, @RequestBody final WorkMessage message)
    {
        final Work work = Work.fromBody(message);
        entityManager.persist(work);
        return new APIResponse<Work>(HttpStatus.CREATED.value(), "Created", work).toResponseEntity();
    }
}
