package com.example.workshop_jpa_617.dao.rep;

import com.example.workshop_jpa_617.dao.AppUserDao;
import com.example.workshop_jpa_617.dao.BookDao;
import com.example.workshop_jpa_617.dao.BookLoanDao;
import com.example.workshop_jpa_617.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@Rollback(value = false)
public class BookLoanDaoTest {
    @Autowired
    private BookLoanDao testObject;


    @Test
    @Transactional
    public void test_bookLoan_successfully(){
        AppUser appUser = new AppUser("username", "password", new Details("email@", "name", LocalDate.parse("1980-09-09")));

        Book book = new Book("isbnr", "title",3);
        book.addAuthor(new Author("aurhorname", "lastname"));
        BookLoan bookLoan = new BookLoan(appUser,book);
        BookLoan savedLoan = testObject.save(bookLoan);
        System.out.println(savedLoan);

    }
}
