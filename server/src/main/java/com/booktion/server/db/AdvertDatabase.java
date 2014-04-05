package com.booktion.server.db;

import com.booktion.server.model.Book;

import java.util.HashMap;
import java.util.Map;

public class AdvertDatabase
{
    private Map<Integer, Book> bookMap;

    public AdvertDatabase()
    {
        bookMap = new HashMap<Integer, Book>();
    }

    public boolean createBook(Book book)
    {
        bookMap.put(book.id, book);

        return true;
    }

    public Book getBook(int id)
    {
        return bookMap.get(id);
    }
}
