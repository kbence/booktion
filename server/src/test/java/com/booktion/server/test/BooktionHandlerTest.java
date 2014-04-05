package com.booktion.server.test;

import com.booktion.log.util.LoggerTestCase;
import com.booktion.server.BooktionHandler;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.thrift.Book;
import com.booktion.thrift.Message;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BooktionHandlerTest extends LoggerTestCase
{
    public AdvertDatabase dbMock;
    public BooktionHandler handler;

    @Before
    public void setUp()
    {
        dbMock = mock(AdvertDatabase.class);
        handler = new BooktionHandler(dbMock);
    }

    private Book createTestBook()
    {
        Book book = new Book();
        book.title = "Testing Explained";
        book.author = "Test Ingur";
        book.publisherId = 1234;
        book.yearOfPublication = 2014;
        return book;
    }

    private com.booktion.server.model.Book thriftToModelBook(Book book)
    {
        com.booktion.server.model.Book modelBook = new com.booktion.server.model.Book();
        modelBook.title = book.title;
        modelBook.author = book.author;
        modelBook.publisherId = book.publisherId;
        modelBook.yearOfPublication = book.yearOfPublication;
        return modelBook;
    }

    @Test
    public void echoShouldLogMessage() throws TException
    {
        // Arrange
        String message = "test message for echo";

        // Act
        handler.echo(new Message(message));

        // Assert
        assertLog("should log the correct string", "CLIENT: " + message);
    }

    @Test
    public void addBookSuccessfullyAddsBookToDatabase() throws TException
    {
        // Arrange
        Book book = createTestBook();
        when(dbMock.createBook(any(com.booktion.server.model.Book.class))).thenReturn(true);

        // Act
        boolean result = handler.addBook(book);

        // Assert
        com.booktion.server.model.Book modelBook = thriftToModelBook(book);
        assertTrue("should return true", result);
        verify(dbMock).createBook(modelBook);
    }

    @Test
    public void addBookFailsToAddBookToDatabase() throws TException
    {
        // Arrange
        Book book = createTestBook();
        when(dbMock.createBook(any(com.booktion.server.model.Book.class))).thenReturn(false);

        // Act
        boolean result = handler.addBook(book);

        // Assert
        com.booktion.server.model.Book modelBook = thriftToModelBook(book);
        assertFalse("should return false", result);
        verify(dbMock).createBook(modelBook);
    }
}
