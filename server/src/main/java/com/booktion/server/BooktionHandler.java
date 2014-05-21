package com.booktion.server;

import com.booktion.server.model.Bookshop;
import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;
import com.booktion.thrift.Book;
import com.booktion.thrift.BooktionService;
import org.apache.thrift.TException;

import java.util.List;

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
    public int addBook(Book book) throws TException
    {
        return bookshop.addBook(book);
    }

    @Override
    public boolean createAdvert(Book book, AdvertType advert, long expires, double price) throws TException
    {
        return bookshop.createAdvert(book, advert, expires, price);
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
    public List<Book> listOwnBooks() throws TException
    {
        return bookshop.listOwnBooks();
    }

    @Override
    public List<Book> listBoughtBooks() throws TException
    {
        return bookshop.listBoughtBooks();
    }

    @Override
    public boolean purchase(int advertId) throws TException
    {
        return bookshop.purchase(advertId);
    }

    @Override
    public boolean bid(int advertId, double price) throws TException
    {
        return bookshop.bid(advertId, price);
    }
}
