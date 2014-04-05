package com.booktion.server.db.test;

import com.booktion.server.db.AdvertDatabase;
import com.booktion.server.model.Book;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AdvertDatabaseTest
{
    @Test
    public void createBookStoresAndGetBookRetrievesBook()
    {
        // Arrange
        AdvertDatabase db = new AdvertDatabase();
        Book book = new Book();
        book.id = 1234;
        book.title = "Testing Explained";
        book.author = "Test Ingur";
        book.publisherId = 2345;
        book.yearOfPublication = 2014;

        // Act
        boolean result = db.createBook(book);

        // Assert
        assertEquals("should return true", true, result);

        Book resultBook = db.getBook(1234);
        assertEquals("id should match", book.id, resultBook.id);
        assertEquals("title should match", book.title, resultBook.title);
        assertEquals("author should match", book.author, resultBook.author);
        assertEquals("publisher should match", book.publisherId, resultBook.publisherId);
        assertEquals("year of publication should match", book.yearOfPublication, resultBook.yearOfPublication);
    }

    @Test
    public void getBookShouldReturnNullIfBookIsMissing()
    {
        // Arrange
        AdvertDatabase db = new AdvertDatabase();

        // Act, Assert
        assertNull("should return null", db.getBook(2345));
    }
}
