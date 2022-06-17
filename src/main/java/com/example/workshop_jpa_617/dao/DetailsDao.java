package com.example.workshop_jpa_617.dao;

import com.example.workshop_jpa_617.entity.Details;

import java.util.List;
import java.util.Optional;

public interface DetailsDao {
    Details save(Details details);

    Optional<Details> findById(int id);

    List<Details> findAll();

    Details update(Details details);

    void remove(Details details);
}
