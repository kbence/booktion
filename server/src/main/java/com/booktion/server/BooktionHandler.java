package com.booktion.server;

import com.booktion.log.Logger;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.server.model.User;
import com.booktion.thrift.Advert;
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

    @Override
    public void logout() throws TException
    {
        UserSession currentSession = sessionManager.getCurrentSession();

        if (currentSession != null) {
            sessionManager.deleteSession(currentSession.user.id);
        }
    }

    @Override
    public boolean addUser(com.booktion.thrift.User user) throws TException
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
        return new LinkedList<Advert>();
    }

    @Override
    public List<Advert> listAdverts(int first, int last) throws TException
    {
        return new LinkedList<Advert>();
    }

    @Override
    public boolean purchase(int bookId) throws TException
    {
        User user = sessionManager.getCurrentSession().user;
        Logger.get().log(String.format("User '%s' purchased book %d", user.username, bookId));
        return true;
    }

    @Override
    public boolean bid(int advertId, double price) throws TException
    {
        User user = sessionManager.getCurrentSession().user;
        db.advert.getAdvert(advertId);
        Logger.get().log(String.format("User %s put a bid on book %d", user.username, advertId));
        return true;
    }
}
