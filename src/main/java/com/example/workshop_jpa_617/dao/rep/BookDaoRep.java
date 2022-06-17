package com.example.workshop_jpa_617.dao.rep;

import com.example.workshop_jpa_617.dao.BookDao;
import com.example.workshop_jpa_617.entity.Book;
import com.example.workshop_jpa_617.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoRep implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b").getResultList();
    }

    @Override
    @Transactional
    public Book update(Book book) {
        findById(book.getId()).orElseThrow(()->new DataNotFoundException("book not found"));
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void remove(Book book) {
        findById(book.getId()).orElseThrow(()->new DataNotFoundException("book not found"));
        entityManager.remove(book);
    }
}
