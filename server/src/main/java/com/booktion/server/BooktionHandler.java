package com.booktion.server;

import com.booktion.log.Logger;
import com.booktion.thrift.*;
import org.apache.thrift.TException;

import java.util.LinkedList;
import java.util.List;

public class BooktionHandler implements BooktionService.Iface
{
    public BooktionHandler()
    {
    }

    @Override
    public Message echo(Message message) throws TException
    {
        Logger.get().log("CLIENT: " + message.getText());

        return new Message("Server is up and running");
    }

    @Override
    public Book getBook(int bookId) throws TException
    {
        return null;
    }

    @Override
    public boolean addBook(Book book) throws TException
    {
        return false;
    }

    @Override
    public Publisher getPublisher(int publisherId) throws TException
    {
        return null;
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
