package com.zqx.test;

import com.zqx.pojo.Book;
import com.zqx.pojo.Page;
import com.zqx.service.BookService;
import com.zqx.service.impl.BookServiceImpl;
import com.zqx.utils.JdbcUtils;
import org.junit.After;
import org.junit.Test;

import java.util.Spliterator;

import static org.junit.Assert.*;

public class BookServiceTest {

    BookService bookService = new BookServiceImpl();

    @After
    public void tearDown() throws Exception {
        JdbcUtils.commitAndClose();
    }

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(24);
        System.out.println(book);
    }

    @Test
    public void page() {
        Page<Book> page = bookService.page(1, 4);
        System.out.println(page);
    }

    @Test
    public void pageByPrice() {
        Page<Book> pageByPrice = bookService.pageByPrice(1, 4, 0, 40);
        System.out.println(pageByPrice);
    }
}