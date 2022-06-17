package com.example.workshop_jpa_617.dao;

import com.example.workshop_jpa_617.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);
    Optional<Book> findById(int id);
    List<Book> findAll();
    Book update(Book book);
    void remove(Book book);
}
