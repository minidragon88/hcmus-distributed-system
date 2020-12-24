package vn.edu.hcmus.master_service.controller;

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
import vn.edu.hcmus.master_service.message.APIResponse;
import vn.edu.hcmus.master_service.message.WorkerStatusMessage;

import java.util.Map;
@RestController
@RequestMapping("/workers")
public class WorkersController
{
    @GetMapping
    public String helloGradle()
    {
        return "Master!";
    }

    @PostMapping("/status")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<APIResponse<String>> register(@RequestHeader final Map<String, String> headers, @RequestBody final WorkerStatusMessage message)
    {
        return new APIResponse<String>(HttpStatus.OK.value(), "Registered successfully", null).toResponseEntity();
    }
}
