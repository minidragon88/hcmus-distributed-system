package vn.edu.hcmus.master_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmus.master_service.model.WorkerNode;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerNode, String>
{
}
