package com.example.workshop_jpa_617.dao.rep;

import com.example.workshop_jpa_617.dao.AuthorDao;
import com.example.workshop_jpa_617.entity.Author;
import com.example.workshop_jpa_617.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoRep implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Author save(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Optional<Author> findById(int id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("select au from Author au").getResultList();
    }

    @Override
    @Transactional
    public Author update(Author author) {
        findById(author.getId()).orElseThrow(()->new DataNotFoundException(("author not found")));
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void remove(Author author) {
        findById(author.getId()).orElseThrow(()->new DataNotFoundException(("author not found")));
        entityManager.remove(author);
    }
}
