package com.booktion.server.db;

import com.booktion.server.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AdvertDatabase
{
    private Connection connection;

    private Map<Integer, Book> bookMap;

    public AdvertDatabase()
    {
        bookMap = new HashMap<Integer, Book>();
    }

    public AdvertDatabase(String filename) throws SQLException
    {
        this();

        connection = DriverManager.getConnection(String.format("jdbc:derby:%1$s;create=true", filename));
        new SchemaCreator(connection).createSchema();
    }

    public boolean createBook(Book book)
    {
        Integer nextId = getNextId();

        try {
            Book copy = (Book) book.clone();
            copy.id = nextId;
            bookMap.put(nextId, copy);
        } catch (CloneNotSupportedException e) {
            return false;
        }

        return true;
    }

    public Book getBook(int id)
    {
        return bookMap.get(id);
    }

    private Integer getNextId()
    {
        Set<Integer> ids = bookMap.keySet();
        Integer i;

        for (i = 1; ids.contains(i);) i++;

        return i;
    }
}
