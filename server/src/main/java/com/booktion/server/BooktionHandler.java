package com.booktion.server;

import com.booktion.log.Logger;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.server.model.User;
import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;
import com.booktion.thrift.Book;
import com.booktion.thrift.BooktionService;
import org.apache.thrift.TException;

import java.util.LinkedList;
import java.util.List;

import static com.booktion.server.model.Book.fromThriftBook;

public class BooktionHandler implements BooktionService.Iface
{
    private AdvertDatabase db;
    private UserSessionManager sessionManager;

    public BooktionHandler(AdvertDatabase db)
    {
        this.db = db;
    }

    public void setSessionManager(UserSessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean login(String username, String password) throws TException
    {
        User user = db.user.findByUsername(username);

        if (user.checkPassword(password)) {
            sessionManager.getCurrentSession().user = user;
            return true;
        }

        return false;
    }

    @Override
    public Book getBook(int bookId) throws TException
    {
        com.booktion.server.model.Book book = db.book.getById(bookId);

        if (book == null)
            return null;

        return book.toThriftBook();
    }

    @Override
    public boolean addBook(Book book) throws TException
    {
        return db.book.create(fromThriftBook(book));
    }

    @Override
    public List<Advert> searchForAdverts(String name) throws TException
    {
        return getHardCodedAdverts();
    }

    @Override
    public List<Advert> listAdverts(int first, int last) throws TException
    {
        return getHardCodedAdverts();
    }

    @Override
    public boolean purchase(int bookId) throws TException
    {
        Logger.get().log("USER purchased book " + bookId);
        return true;
    }

    @Override
    public boolean bid(int bookId) throws TException
    {
        Logger.get().log("USER put a bid on book " + bookId);
        return true;
    }

    private LinkedList<Advert> getHardCodedAdverts()
    {
        LinkedList<Advert> adverts = new LinkedList<Advert>();

        adverts.add(
            new Advert(
                1, 1, new Book(
                    1,
                    "A C++ Programozási nyelv",
                    "Bjarne Stroustrup",
                    "Kiskapu",
                    (short)1982,
                    (short)84
                ),
                AdvertType.AUCTION, 4300
            )
        );

        adverts.add(
            new Advert(
                1, 1, new Book(
                    1,
                    "Számítógép-hálózatok",
                    "Andrew S. Tanenbaum",
                    "Addison-Weasley",
                    (short)1992,
                    (short)45
                ),
                AdvertType.FIX_PRICE, 2590
            )
        );
        return adverts;
    }
}
