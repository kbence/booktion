package com.booktion.server.db;

import net.sf.log4jdbc.ConnectionSpy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdvertDatabase
{
    public BookDAO book;
    public UserDAO user;
    public AdvertDAO advert;
    public BidDAO bid;

    public AdvertDatabase(String filename, boolean debug) throws SQLException
    {
        Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%1$s", filename));

        if (debug) {
            connection = new ConnectionSpy(connection);
        }

        new SchemaCreator(connection).createSchema();

        book = new BookDAO(connection);
        user = new UserDAO(connection);
        bid = new BidDAO(connection);
        advert = new AdvertDAO(connection, user, book, bid);
    }

}
