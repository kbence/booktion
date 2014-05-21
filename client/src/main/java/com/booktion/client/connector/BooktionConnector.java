package com.booktion.client.connector;

import com.booktion.thrift.*;
import org.apache.thrift.TException;

import java.util.List;

public class BooktionConnector
{
    private ClientFactory protocolFactory;
    private BooktionService.Client client;

    public BooktionConnector()
    {
        protocolFactory = new ClientFactory();
    }

    public void setProtocolFactory(ClientFactory factory)
    {
        protocolFactory = factory;
    }

    public void connect(String host, int port) throws TException
    {
        client = protocolFactory.createClient(host, port);
    }

    public boolean login(String username, String password) throws TException
    {
        return client.login(username, password);
    }

    public void logout() throws TException
    {
        client.logout();
    }

    public Book getBook(int bookId) throws TException
    {
        return client.getBook(bookId);
    }

    public boolean addUser(User user) throws TException
    {
        return client.addUser(user);
    }
    public boolean createAdvert(Book book, AdvertType type, long expires, double price) throws TException
    {
        return client.createAdvert(book, type, expires, price);
    }

    public List<Advert> listAdverts(int first, int last) throws TException
    {
        return client.listAdverts(first, last);
    }

    public List<Advert> searchForAdverts(String name) throws TException
    {
        return client.searchForAdverts(name);
    }

    public List<Book> listOwnBooks() throws TException
    {
        return client.listOwnBooks();
    }

    public List<Book> listBoughtBooks() throws TException
    {
        return client.listBoughtBooks();
    }

    public boolean purchase(int advertId) throws TException
    {
        return client.purchase(advertId);
    }

    public boolean bid(int advertId, double price) throws TException
    {
        return client.bid(advertId, price);
    }
}
