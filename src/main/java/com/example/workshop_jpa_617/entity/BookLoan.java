package com.example.workshop_jpa_617.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate loanDate;
    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private boolean returned;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private AppUser borrower;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Book book;

    public BookLoan() {
        setLoanDate(LocalDate.now());
    }

    public BookLoan (AppUser borrower, Book book) {
        this();
        setBorrower(borrower);
        setBook(book);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<0) throw new IllegalArgumentException("id must be 0 or more");
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        if(loanDate==null) throw new IllegalArgumentException("loanDate was null");
        this.loanDate = loanDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if(dueDate==null) throw new IllegalArgumentException("dueDate is null");
        this.dueDate = dueDate;
    }
    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        if(borrower==null) throw new IllegalArgumentException("borrower was null");
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if(book==null) throw new IllegalArgumentException("Book was null");
        setDueDate(loanDate.plusDays(book.getMaxLoanDays()));
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return id == bookLoan.id && returned == bookLoan.returned && loanDate.equals(bookLoan.loanDate) && borrower.equals(bookLoan.borrower) && book.equals(bookLoan.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanDate, returned, borrower, book);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id=" + id +
                ", loanDate=" + loanDate +
                ", returned=" + returned +
                ", borrower=" + borrower +
                ", book=" + book +
                '}';
    }
}
