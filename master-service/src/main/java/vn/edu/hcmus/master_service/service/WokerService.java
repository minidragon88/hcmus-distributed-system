package vn.edu.hcmus.master_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmus.master_service.model.Worker;
import vn.edu.hcmus.master_service.repository.WorkerRepository;

import java.util.List;

@Service
public class WokerService
{
    @Autowired
    private WorkerRepository repository;

    public List<Worker> findAll()
    {
        return repository.findAll();
    }
}
