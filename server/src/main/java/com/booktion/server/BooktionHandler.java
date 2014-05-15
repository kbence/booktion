package com.booktion.server;

import com.booktion.log.Logger;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.server.model.Bookshop;
import com.booktion.server.model.User;
import com.booktion.thrift.Advert;
import com.booktion.thrift.Book;
import com.booktion.thrift.BooktionService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.booktion.server.model.Book.fromThriftBook;

public class BooktionHandler implements BooktionService.Iface
{
    private Bookshop bookshop;

    public BooktionHandler(Bookshop bookshop)
    {
        this.bookshop = bookshop;
    }

    public void setBookshop(Bookshop bookshop)
    {
        this.bookshop = bookshop;
    }

    @Override
    public boolean login(String username, String password) throws TException
    {
        return bookshop.login(username, password);
    }

    @Override
    public void logout() throws TException
    {
        bookshop.logout();
    }

    @Override
    public boolean addUser(com.booktion.thrift.User user) throws TException
    {
        return bookshop.addUser(user);
    }

    @Override
    public Book getBook(int bookId) throws TException
    {
        return bookshop.getBook(bookId);
    }

    @Override
    public boolean addBook(Book book) throws TException
    {
        return bookshop.addBook(book);
    }

    @Override
    public List<Advert> searchForAdverts(String title) throws TException
    {
        return bookshop.searchForAdverts(title);
    }

    @Override
    public List<Advert> listAdverts(int first, int last) throws TException
    {
        return bookshop.listAdverts();
    }

    @Override
    public boolean purchase(int bookId) throws TException
    {
        return bookshop.purchase(bookId);
    }

    @Override
    public boolean bid(int advertId, double price) throws TException
    {
        return bookshop.bid(advertId, price);
    }
}
