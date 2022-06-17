package com.example.workshop_jpa_617.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int maxLoanDays;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "writtenBooks")
    private List<Author> authors;

    public Book() {
    }

    public Book(String isbn, String title, int maxLoanDays) {
        setIsbn(isbn);
        setTitle(title);
        setMaxLoanDays(maxLoanDays);
    }

    public Book(String isbn, String title, int maxLoanDays, List<Author> authors) {
        this(isbn, title, maxLoanDays);
        setAuthors(authors);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<0) throw new IllegalArgumentException("id must be 0 or more");
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(isbn==null) throw new IllegalArgumentException("isbn was null");
        if(isbn.trim().isEmpty()) throw new IllegalArgumentException("isbn was empty");
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title==null) throw new IllegalArgumentException("title was null");
        if(title.trim().isEmpty()) throw new IllegalArgumentException("title was empty");
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        if(maxLoanDays<0) throw new IllegalArgumentException("maxLoanDays must be 0 or more");
        this.maxLoanDays = maxLoanDays;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        if(authors==null) throw new IllegalArgumentException("authors is null");
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && maxLoanDays == book.maxLoanDays && isbn.equals(book.isbn) && title.equals(book.title) && authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, maxLoanDays, authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                ", authors=" + authors +
                '}';
    }

    public void addAuthor(Author author) {
        if (authors == null) authors = new ArrayList<>();
        if (!authors.contains(author)) authors.add(author);
        if (author.getWrittenBooks() == null) author.setWrittenBooks(new ArrayList<>());
        if (!author.getWrittenBooks().contains(this)) author.getWrittenBooks().add(this);
    }

}
