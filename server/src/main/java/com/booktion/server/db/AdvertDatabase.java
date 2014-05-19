package com.booktion.server.db;

import com.booktion.server.model.Book;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdvertDatabase
{
    public BookDAO book;
    public UserDAO user;
    public AdvertDAO advert;
    public BidDAO bid;

    public AdvertDatabase(String filename) throws SQLException
    {
        Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%1$s", filename));
        new SchemaCreator(connection).createSchema();

        book = new BookDAO(connection);
        user = new UserDAO(connection);
        bid = new BidDAO(connection);
        advert = new AdvertDAO(connection, book, bid);
    }

}
