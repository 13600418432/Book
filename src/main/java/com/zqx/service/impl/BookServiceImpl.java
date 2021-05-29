package com.zqx.service.impl;

import com.zqx.dao.BookDao;
import com.zqx.dao.impl.BookDaoImpl;
import com.zqx.pojo.Book;
import com.zqx.pojo.Page;
import com.zqx.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBook(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        page.setPageSize(pageSize);
        Integer PageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0)
            PageTotal++;
        page.setPageTotal(PageTotal);
        page.setPageNo(pageNo);
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        page.setPageSize(pageSize);
        Integer PageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0)
            PageTotal++;
        page.setPageTotal(PageTotal);
        page.setPageNo(pageNo);
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
