package com.booktion.client.connector;

import com.booktion.thrift.Advert;
import com.booktion.thrift.Book;
import com.booktion.thrift.BooktionService;
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

    public List<Advert> listAdverts(int first, int last) throws TException
    {
        return client.listAdverts(first, last);
    }

    public List<Advert> searchForAdverts(String name) throws TException
    {
        return client.searchForAdverts(name);
    }

    public boolean purchase(Book book) throws TException
    {
        return client.purchase(book.id);
    }

    public boolean bid(Book book) throws TException
    {
        return client.bid(book.id);
    }
}
