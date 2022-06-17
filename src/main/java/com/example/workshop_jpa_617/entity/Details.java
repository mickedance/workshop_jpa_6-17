package com.example.workshop_jpa_617.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, updatable = false)
    private String email;
    private String name;
    private LocalDate birthDay;

    public Details() {
    }

    public Details(String email, String name, LocalDate birthDay) {
        setEmail(email);
        setName(name);
        setBirthDay(birthDay);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id must be 0 or more");
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email was null");
        if (email.trim().isEmpty()) throw new IllegalArgumentException("email was empty");
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name was null");
        if (name.trim().isEmpty()) throw new IllegalArgumentException("name was empty");
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        if (birthDay == null) throw new IllegalArgumentException("birthday was null");
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return id == details.id && email.equals(details.email) && Objects.equals(name, details.name) && Objects.equals(birthDay, details.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, birthDay);
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
