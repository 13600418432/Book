package com.zqx.service;

import com.zqx.pojo.Book;
import com.zqx.pojo.Page;

import java.util.List;

public interface BookService {

    public int addBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public Page<Book> page(int pageNo,int pageSize);

    public Page<Book> pageByPrice(int pageNo,int pageSize,int min,int max);

}
