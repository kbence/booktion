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

        return book.id == id && isStringEquals(title, book.title) &&
                isStringEquals(author, book.author) &&
                isStringEquals(publisher, book.publisher) &&
                book.yearOfPublication == yearOfPublication &&
                book.condition == condition;
    }

    private boolean isStringEquals(String firstString, String secondString)
    {
        return firstString == null && secondString == null || firstString.equals(secondString);
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
    public Object clone() throws CloneNotSupportedException
    {
        Book book = (Book)super.clone();

        book.id = id;
        book.title = title;
        book.author = author;
        book.publisher = publisher;
        book.yearOfPublication = yearOfPublication;
        book.condition = condition;

        return book;
    }

    @Override
    public String toString()
    {
        return String.format(
            "%d/%s/%s/%s/%d/%d",
            id, title, author, publisher, yearOfPublication, condition
        );
    }
}
