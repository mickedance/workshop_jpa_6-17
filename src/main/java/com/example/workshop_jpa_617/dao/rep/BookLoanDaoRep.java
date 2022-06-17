package com.example.workshop_jpa_617.dao.rep;

import com.example.workshop_jpa_617.dao.BookLoanDao;
import com.example.workshop_jpa_617.entity.BookLoan;
import com.example.workshop_jpa_617.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BookLoanDaoRep implements BookLoanDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public BookLoan save(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    public Optional<BookLoan> findById(int id) {
        return Optional.ofNullable(entityManager.find(BookLoan.class, id));
    }

    @Override
    public List<BookLoan> findAll() {
        return entityManager.createQuery("select bl from BookLoan bl").getResultList();
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        findById(bookLoan.getId()).orElseThrow(()->new DataNotFoundException("bookLoan was not found"));
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void remove(BookLoan bookLoan) {
        findById(bookLoan.getId()).orElseThrow(()->new DataNotFoundException("bookLoan was not found"));
        entityManager.remove(bookLoan);

    }
}
