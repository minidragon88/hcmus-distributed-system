package vn.edu.hcmus.master_service.controller.work;

import org.springframework.http.ResponseEntity;
import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.message.WorkMessage;
import vn.edu.hcmus.master_service.model.Work;

import javax.persistence.EntityManager;

public interface WorkAccepter
{
    public ResponseEntity<APIResponse<Work>> accept(EntityManager em, WorkMessage message);
}
