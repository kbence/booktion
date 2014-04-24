package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.thrift.Advert;

import java.util.List;

public class MainController
{
    private BooktionConnector connector;

    private List<Advert> adverts;

    public MainController()
    {
        connect();
        loadAdvertList();
    }

    private void loadAdvertList()
    {
    }

    private void connect()
    {
    }

    public BooktionConnector getConnector()
    {
        return connector;
    }
}
