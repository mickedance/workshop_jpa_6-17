package com.example.workshop_jpa_617.dao;

import com.example.workshop_jpa_617.entity.BookLoan;

import java.util.List;
import java.util.Optional;

public interface BookLoanDao {
    BookLoan save(BookLoan bookLoan);

    Optional<BookLoan> findById(int id);

    List<BookLoan> findAll();

    BookLoan update(BookLoan bookLoan);

    void remove(BookLoan bookLoan);
}
