package com.example.workshop_jpa_617.entity;

import org.hibernate.metamodel.model.domain.IdentifiableDomainType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Book> writtenBooks;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Author(String firstName, String lastName, List<Book> writtenBooks) {
        this(firstName, lastName);
        setWrittenBooks(writtenBooks);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<0) throw new IllegalArgumentException("id must be 0 or more");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName==null) throw new IllegalArgumentException("firstname was null");
        if(firstName.trim().isEmpty()) throw new IllegalArgumentException("firstname was empty");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName==null) throw new IllegalArgumentException("lastName was null");
        if(lastName.trim().isEmpty()) throw new IllegalArgumentException("lastName was empty");
        this.lastName = lastName;
    }

    public List<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(List<Book> writtenBooks) {
        if(writtenBooks==null) throw new IllegalArgumentException("writtenBooks was null");
        this.writtenBooks = writtenBooks;
    }

    public void addBook(Book book){
        if(writtenBooks==null) writtenBooks = new ArrayList<>();
        if(!writtenBooks.contains(book)) writtenBooks.add(book);
        if(book.getAuthors() == null) book.setAuthors(new ArrayList<>());
        if(!book.getAuthors().contains(this)) book.getAuthors().add(this);
    }
}
