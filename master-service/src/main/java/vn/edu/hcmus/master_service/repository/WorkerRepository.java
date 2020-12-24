package vn.edu.hcmus.master_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmus.master_service.model.Worker;
import vn.edu.hcmus.master_service.model.WorkerId;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, WorkerId>
{
}
