package com.booktion.server.db;

import com.booktion.server.model.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdvertDatabase
{
    private Map<Integer, Book> bookMap;

    public AdvertDatabase()
    {
        bookMap = new HashMap<Integer, Book>();
    }

    public boolean createBook(Book book)
    {
        Integer nextId = getNextId();

        Book copy = (Book)book.clone();
        copy.id = nextId;
        bookMap.put(nextId, copy);

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
