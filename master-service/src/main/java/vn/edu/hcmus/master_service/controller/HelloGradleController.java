package vn.edu.hcmus.master_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloGradleController
{
    @GetMapping
    public String helloGradle()
    {
        return "Master!";
    }
}
