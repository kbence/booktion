package com.booktion.server.model;

public class Book
{
    public int id;
    public String title;
    public String author;
    public int publisherId;
    public short yearOfPublication;

    public com.booktion.thrift.Book toThriftBook()
    {
        com.booktion.thrift.Book book = new com.booktion.thrift.Book();

        book.id = id;
        book.title = title;
        book.author = author;
        book.publisherId = publisherId;
        book.yearOfPublication = yearOfPublication;

        return book;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Book))
            return false;

        Book book = (Book)object;

        return book.id == id && book.title.equals(title) && book.author.equals(author) &&
                book.publisherId == publisherId && book.yearOfPublication == yearOfPublication;
    }

    public static Book fromThriftBook(com.booktion.thrift.Book thriftBook)
    {
        Book book = new Book();

        book.id = thriftBook.id;
        book.title = thriftBook.title;
        book.author = thriftBook.author;
        book.publisherId = thriftBook.publisherId;
        book.yearOfPublication = thriftBook.yearOfPublication;

        return book;
    }
}
