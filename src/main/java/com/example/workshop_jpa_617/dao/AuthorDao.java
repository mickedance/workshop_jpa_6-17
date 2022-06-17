package com.example.workshop_jpa_617.dao;

import com.example.workshop_jpa_617.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);
    Optional<Author> findById(int id);
    List<Author> findAll();
    Author update(Author author);
    void remove(Author author);
}
