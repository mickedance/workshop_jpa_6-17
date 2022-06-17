package com.example.workshop_jpa_617.dao.rep;

import com.example.workshop_jpa_617.dao.DetailsDao;
import com.example.workshop_jpa_617.entity.Details;
import com.example.workshop_jpa_617.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class DetailsDaoRep implements DetailsDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Details save(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    public Optional<Details> findById(int id) {
        return Optional.ofNullable(entityManager.find(Details.class,id));
    }

    @Override
    public List<Details> findAll() {
        return entityManager.createQuery("select d from Details d").getResultList();
    }

    @Override
    @Transactional
    public Details update(Details details) {
        findById(details.getId()).orElseThrow(()->new DataNotFoundException("details not found"));
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void remove(Details details) {
        findById(details.getId()).orElseThrow(()->new DataNotFoundException("details not found"));
        entityManager.remove(details);
    }
}
