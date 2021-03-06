package com.booktion.server.model;

import com.booktion.log.Logger;
import com.booktion.server.UserSession;
import com.booktion.server.UserSessionManager;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.thrift.AdvertType;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.booktion.server.model.Book.fromThriftBook;

public class Bookshop
{
    private AdvertDatabase db;
    private UserSessionManager sessionManager;

    public Bookshop(AdvertDatabase db)
    {
        this.db = db;
    }

    public void setSessionManager(UserSessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    public boolean login(String username, String password)
    {
        Logger.get().log(String.format("User %s tries to log in", username));
        User user = db.user.findByUsername(username);

        Logger.get().log(String.format("%s", user));

        if (user != null && user.checkPassword(password)) {
            sessionManager.getCurrentSession().user = user;
            Logger.get().log(String.format("User %s logged in", username));
            return true;
        }

        return false;
    }

    public void logout()
    {
        UserSession currentSession = sessionManager.getCurrentSession();

        if (currentSession != null) {
            sessionManager.deleteSession(currentSession.user.id);
        }
    }

    public boolean addUser(com.booktion.thrift.User user)
    {
        Logger.get().log(String.format("Trying to create user %s", user.username));
        User modelUser = new User();
        modelUser.username = user.username;
        modelUser.password = user.password;
        modelUser.forename = user.forename;
        modelUser.surname = user.surname;
        modelUser.address = user.address;

        return db.user.createUser(modelUser);
    }

    public com.booktion.thrift.Book getBook(int bookId)
    {
        com.booktion.server.model.Book book = db.book.getById(bookId);

        if (book == null)
            return null;

        return book.toThriftBook();
    }

    public int addBook(com.booktion.thrift.Book book)
    {
        return db.book.create(fromThriftBook(book));
    }

    public boolean createAdvert(com.booktion.thrift.Book book, AdvertType advert, long expires, double price) throws TException
    {
        UserSession session = sessionManager.getCurrentSession();

        Book modelBook = Book.fromThriftBook(book);
        modelBook.owner = session.user.id;

        Advert adv = new Advert(0, session.user,
                modelBook, Advert.AdvertType.fromThrift(advert),
                new Date(expires), price, 0);

        return db.advert.create(adv);
    }

    public List<com.booktion.thrift.Advert> searchForAdverts(String keyword)
    {
        List<com.booktion.thrift.Advert> advertList = new ArrayList<com.booktion.thrift.Advert>();
        Logger.get().log("Searching for advert");

        for (Advert advert : db.advert.searchForAdverts(keyword)) {
            advertList.add(advert.toThrift());
        }

        return advertList;
    }

    public List<com.booktion.thrift.Advert> listAdverts()
    {
        List<com.booktion.thrift.Advert> adverts = new ArrayList<com.booktion.thrift.Advert>();

        for (com.booktion.server.model.Advert advert : db.advert.list()) {
            adverts.add(advert.toThrift());
        }

        return adverts;
    }

    public List<com.booktion.thrift.Book> listOwnBooks()
    {
        UserSession session = sessionManager.getCurrentSession();
        List<com.booktion.thrift.Book> books = new ArrayList<com.booktion.thrift.Book>();

        if (session != null && session.user != null) {
            for (Book book : db.book.getByOwner(session.user.id)) {
                books.add(book.toThriftBook());
            }
        }

        return books;
    }

    public List<com.booktion.thrift.Book> listBoughtBooks()
    {
        UserSession session = sessionManager.getCurrentSession();
        ArrayList<com.booktion.thrift.Book> books = new ArrayList<com.booktion.thrift.Book>();

        if (session != null && session.user != null) {
            for (Advert advert : db.advert.listByWinner(session.user.id)) {
                books.add(advert.book.toThriftBook());
            }
        }

        return books;
    }

    public boolean purchase(int advertId)
    {
        User user = sessionManager.getCurrentSession().user;
        Logger.get().log(String.format("User '%s' purchased book %d", user.username, advertId));

        if (db.advert.getById(advertId).issuer.id == user.id)
            return false;

        return db.advert.setWinner(advertId, user.id);
    }

    public boolean bid(int advertId, double price)
    {
        User user = sessionManager.getCurrentSession().user;
        Advert advert = db.advert.getById(advertId);

        if (db.advert.getById(advertId).issuer.id == user.id)
            return false;

        System.out.println(String.format("advertId: %d, advert: %s", advertId, advert));

        Logger.get().log(String.format("User %s put a bid on book %d", user.username, advertId));

        Bid bid = new Bid();
        bid.advertId = advertId;
        bid.userId = user.id;
        bid.price = price;

        if (price <= db.bid.getHighestPrice(advertId) || price < advert.price)
            return false;

        return db.bid.put(bid) && db.advert.setWinner(bid.advertId, user.id);
    }
}
