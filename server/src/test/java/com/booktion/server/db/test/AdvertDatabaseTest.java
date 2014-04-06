package com.booktion.server.db.test;

import com.booktion.server.db.AdvertDatabase;
import com.booktion.server.model.Book;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdvertDatabaseTest
{
    private AdvertDatabase db;

    @Before
    public void setUp()
    {
        db = new AdvertDatabase();
    }

    @Test
    public void createBookStoresAndGetBookRetrievesBook()
    {
        // Arrange
        Book book = new Book();
        book.title = "Testing Explained";
        book.author = "Test Ingur";
        book.publisherId = 2345;
        book.yearOfPublication = 2014;

        // Act
        boolean result = db.createBook(book);

        // Assert
        assertEquals("should return true", true, result);

        Book resultBook = db.getBook(1);
        assertEquals("id should be 1", 1, resultBook.id);
        assertEquals("title should match", book.title, resultBook.title);
        assertEquals("author should match", book.author, resultBook.author);
        assertEquals("publisher should match", book.publisherId, resultBook.publisherId);
        assertEquals("year of publication should match", book.yearOfPublication, resultBook.yearOfPublication);
    }

    @Test
    public void createBookShouldFindAnUnusedBookId()
    {
        // Arrange
        Book book1 = new Book();
        Book book2 = new Book();

        book1.id = 1;
        book2.id = 1;
        book2.title = "Book of getting new ID";

        db.createBook(book1);

        // Act
        db.createBook(book2);

        // Assert
        Book savedBook = db.getBook(2);
        assertNotNull("should return a book", savedBook);
        assertEquals("saved book title should match", book2.title, savedBook.title);
        assertEquals("original book id should not be modified", 1, book2.id);
    }

    @Test
    public void getBookShouldReturnNullIfBookIsMissing()
    {
        // Act, Assert
        assertNull("should return null", db.getBook(2345));
    }
}
