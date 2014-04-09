package com.booktion.server.model;

public class Book implements Cloneable
{
    public int id;
    public String title;
    public String author;
    public String publisher;
    public short yearOfPublication;
    public short condition;

    public com.booktion.thrift.Book toThriftBook()
    {
        com.booktion.thrift.Book book = new com.booktion.thrift.Book();

        book.id = id;
        book.title = title;
        book.author = author;
        book.publisher = publisher;
        book.yearOfPublication = yearOfPublication;
        book.condition = condition;

        return book;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Book))
            return false;

        Book book = (Book)object;

        return book.id == id && book.title.equals(title) && book.author.equals(author) &&
                book.publisher == publisher && book.yearOfPublication == yearOfPublication &&
                book.condition == condition;
    }

    public static Book fromThriftBook(com.booktion.thrift.Book thriftBook)
    {
        Book book = new Book();

        book.id = thriftBook.id;
        book.title = thriftBook.title;
        book.author = thriftBook.author;
        book.publisher = thriftBook.publisher;
        book.yearOfPublication = thriftBook.yearOfPublication;
        book.condition = thriftBook.condition;

        return book;
    }

    @Override
    public Object clone()
    {
        Book book = new Book();

        book.id = id;
        book.title = title;
        book.author = author;
        book.publisher = publisher;
        book.yearOfPublication = yearOfPublication;
        book.condition = condition;

        return book;
    }
}
