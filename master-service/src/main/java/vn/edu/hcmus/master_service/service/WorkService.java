package vn.edu.hcmus.master_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmus.commons.message.WorkStatus;
import vn.edu.hcmus.master_service.model.Work;
import vn.edu.hcmus.master_service.repository.WorkRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

@Service
public class WorkService
{
    @Autowired
    private WorkRepository repository;
    @Autowired
    private EntityManager em;

    public List<Work> findAll()
    {
        return repository.findAll();
    }

    public Work findById(final String id)
    {
        return repository.findById(id).orElse(null);
    }

    public List<Work> findTopWorkByStatusAndCreatedTime(final int limit, final WorkStatus status)
    {
        final TypedQuery<Work> query = em.createQuery("SELECT w FROM Work WHERE status = ?1 w ORDER BY w.created_time", Work.class);
        return query.setParameter(1, status.name()).setMaxResults(limit).getResultList();
    }

    public List<Work> findTopWorkByStatusAndLastUpdatedTime(final int limit, final WorkStatus status)
    {
        final TypedQuery<Work> query = em.createQuery("SELECT w FROM Work WHERE status = ?1 w ORDER BY w.last_updated_time", Work.class);
        return query.setParameter(1, status.name()).setMaxResults(limit).getResultList();
    }
}
