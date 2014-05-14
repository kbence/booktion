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

    public AdvertDatabase(String filename) throws SQLException
    {
        Connection connection = DriverManager.getConnection(String.format("jdbc:derby:%1$s;create=true", filename));
        new SchemaCreator(connection).createSchema();

        book = new BookDAO(connection);
        user = new UserDAO(connection);
    }

}
