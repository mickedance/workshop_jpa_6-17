package com.example.workshop_jpa_617.entity;

import org.hibernate.metamodel.model.domain.IdentifiableDomainType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate regDate;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Details userDetails;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "borrower")
    private List<BookLoan> bookLoans;

    public AppUser() {
        setRegDate(LocalDate.now());
    }

    public AppUser(String username, String password, Details userDetails) {
        this();
        setUsername(username);
        setPassword(password);
        setUserDetails(userDetails);
    }

    public AppUser(String username, String password, Details userDetails, List<BookLoan> bookLoans) {
        this(username, password, userDetails);
        setBookLoans(bookLoans);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id must be 0 or more");
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null) throw new IllegalArgumentException("username was null");
        if (username.trim().isEmpty()) throw new IllegalArgumentException("username was empty");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new IllegalArgumentException("password was null");
        if (password.trim().isEmpty()) throw new IllegalArgumentException("password was empty");
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        if (regDate == null) throw new IllegalArgumentException("regDate was null");

        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        if (userDetails == null) throw new IllegalArgumentException("userDetails was null");
        this.userDetails = userDetails;
    }

    public List<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void setBookLoans(List<BookLoan> bookLoans) {
        if (bookLoans == null) throw new IllegalArgumentException("bookLoans was null");
        this.bookLoans = bookLoans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && username.equals(appUser.username) && password.equals(appUser.password) && regDate.equals(appUser.regDate) && userDetails.equals(appUser.userDetails) && Objects.equals(bookLoans, appUser.bookLoans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, regDate, userDetails, bookLoans);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                ", bookLoans=" + bookLoans +
                '}';
    }
}
