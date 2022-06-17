package com.example.workshop_jpa_617.dao;

import com.example.workshop_jpa_617.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {
    AppUser save(AppUser appUser);

    Optional<AppUser> findById(int id);

    List<AppUser> findAll();

    AppUser update(AppUser appUser);

    void remove(AppUser appUser);
}
