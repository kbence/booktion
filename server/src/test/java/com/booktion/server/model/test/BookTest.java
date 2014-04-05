package com.booktion.server.model.test;

import com.booktion.server.model.Book;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest
{
    private Book createTestBook()
    {
        Book book = new Book();
        book.id = 1234;
        book.author = "Alpha";
        book.title = "Beta";
        book.publisherId = 2345;
        book.yearOfPublication = 2014;
        return book;
    }

    @Test
    public void toThriftBookShouldCreateMatchingObject()
    {
        // Arrange
        Book book = createTestBook();

        // Act
        com.booktion.thrift.Book thriftBook = book.toThriftBook();

        // Assert
        assertEquals("id should match", book.id, thriftBook.id);
        assertEquals("author should match", book.author, thriftBook.author);
        assertEquals("title should match", book.title, thriftBook.title);
        assertEquals("publisher id should match", book.publisherId, thriftBook.publisherId);
        assertEquals("year of publication should match", book.yearOfPublication, thriftBook.yearOfPublication);
    }

    @Test
    public void fromThriftBookShouldCreateMatchingObject()
    {
        // Arrange
        com.booktion.thrift.Book book = new com.booktion.thrift.Book();
        book.id = 1234;
        book.author = "Alpha";
        book.title = "Beta";
        book.publisherId = 2345;
        book.yearOfPublication = 2014;

        // Act
        Book modelBook = Book.fromThriftBook(book);

        // Assert
        assertEquals("id should match", book.id, modelBook.id);
        assertEquals("author should match", book.author, modelBook.author);
        assertEquals("title should match", book.title, modelBook.title);
        assertEquals("publisher id should match", book.publisherId, modelBook.publisherId);
        assertEquals("year of publication should match", book.yearOfPublication, modelBook.yearOfPublication);
    }

    @Test
    public void equalsShouldReturnTrueWhenFieldsMatch()
    {
        // Arrange
        Book book1 = createTestBook();
        Book book2 = Book.fromThriftBook(book1.toThriftBook());

        // Act, Assert
        assertTrue("should return true when fields match", book1.equals(book2));
    }

    @Test
    public void equalsShouldReturnFalseWhenItsNotABook()
    {
        // Arrange
        Book book = createTestBook();

        // Act, Assert
        assertFalse("should return true when it's not a book", book.equals(new Object()));
    }

    @Test
    public void equalsShouldReturnFalseWhenIdMismatches()
    {
        // Arrange
        Book book1 = createTestBook();
        Book book2 = Book.fromThriftBook(book1.toThriftBook());
        book2.id = 4567;

        // Act, Assert
        assertFalse("should return false when id mismatches", book1.equals(book2));
    }

    @Test
    public void equalsShouldReturnFalseWhenTitleMismatches()
    {
        // Arrange
        Book book1 = createTestBook();
        Book book2 = Book.fromThriftBook(book1.toThriftBook());
        book2.title = "Another title";

        // Act, Assert
        assertFalse("should return false when title mismatches", book1.equals(book2));
    }

    @Test
    public void equalsShouldReturnFalseWhenAuthorMismatches()
    {
        // Arrange
        Book book1 = createTestBook();
        Book book2 = Book.fromThriftBook(book1.toThriftBook());
        book2.author = "Another Author";

        // Act, Assert
        assertFalse("should return false when author mismatches", book1.equals(book2));
    }

    @Test
    public void equalsShouldReturnFalseWhenPublisherIdMismatches()
    {
        // Arrange
        Book book1 = createTestBook();
        Book book2 = Book.fromThriftBook(book1.toThriftBook());
        book2.publisherId = 3467;

        // Act, Assert
        assertFalse("should return false when publisher id mismatches", book1.equals(book2));
    }

    @Test
    public void equalsShouldReturnFalseWhenYearOfPublicationMismatches()
    {
        // Arrange
        Book book1 = createTestBook();
        Book book2 = Book.fromThriftBook(book1.toThriftBook());
        book2.yearOfPublication = 2013;

        // Act, Assert
        assertFalse("should return false when year of publication mismatches", book1.equals(book2));
    }
}
