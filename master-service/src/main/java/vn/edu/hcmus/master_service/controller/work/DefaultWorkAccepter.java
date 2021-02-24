package vn.edu.hcmus.master_service.controller.work;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.message.WorkMessage;
import vn.edu.hcmus.master_service.model.Work;

import javax.persistence.EntityManager;

public class DefaultWorkAccepter implements WorkAccepter
{
    @Override
    public ResponseEntity<APIResponse<Work>> accept(final EntityManager em, final WorkMessage message)
    {
        final Work work = Work.fromBody(message);
        em.persist(work);
        return new APIResponse<Work>(HttpStatus.CREATED.value(), "Created", work).toResponseEntity();
    }
}
