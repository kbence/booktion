package com.booktion.server;

import com.booktion.log.Logger;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.thrift.*;
import org.apache.thrift.TException;

import java.util.LinkedList;
import java.util.List;

import static com.booktion.server.model.Book.fromThriftBook;

public class BooktionHandler implements BooktionService.Iface
{
    private AdvertDatabase db;

    public BooktionHandler(AdvertDatabase db)
    {
        this.db = db;
    }

    @Override
    public Book getBook(int bookId) throws TException
    {
        com.booktion.server.model.Book book = db.getBook(bookId);

        if (book == null)
            return null;

        return book.toThriftBook();
    }

    @Override
    public boolean addBook(Book book) throws TException
    {
        return db.createBook(fromThriftBook(book));
    }

    @Override
    public List<Advert> searchForAdverts(String name) throws TException
    {
        return new LinkedList<Advert>();
    }

    @Override
    public List<Advert> listAdverts(int first, int last) throws TException
    {
        return new LinkedList<Advert>();
    }
}
