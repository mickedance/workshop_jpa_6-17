package com.example.workshop_jpa_617.dao.rep;

import com.example.workshop_jpa_617.dao.AppUserDao;
import com.example.workshop_jpa_617.entity.AppUser;
import com.example.workshop_jpa_617.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDaoReo implements AppUserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AppUser save(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public Optional<AppUser> findById(int id) {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }

    @Override
    public List<AppUser> findAll() {
        return entityManager.createQuery("select a from AppUser  a").getResultList();
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        findById(appUser.getId()).orElseThrow(()->new DataNotFoundException("appUser not found"));
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void remove(AppUser appUser) {
        findById(appUser.getId()).orElseThrow(()->new DataNotFoundException("appUser not found"));
        entityManager.remove(appUser);
    }
}
