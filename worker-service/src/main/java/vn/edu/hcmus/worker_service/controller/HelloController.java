package vn.edu.hcmus.worker_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/")
public class HelloController
{
    @Autowired
    private Environment env;
    @GetMapping
    public String helloGradle() throws IOException
    {
        return "Worker!";
    }
}
