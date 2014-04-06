package com.booktion.server.test;

import com.booktion.log.util.LoggerTestCase;
import com.booktion.server.BooktionHandler;
import com.booktion.server.db.AdvertDatabase;
import com.booktion.thrift.Book;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import static com.booktion.server.model.Book.fromThriftBook;
import static junit.framework.Assert.*;
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

    @Test
    public void getBookReturnsMatchingBook() throws TException
    {
        // Arrange
        Book book = createTestBook();
        when(dbMock.getBook(book.id)).thenReturn(fromThriftBook(book));

        // Act
        Book result = handler.getBook(book.id);

        // Assert
        assertEquals("book should match", book, result);
        verify(dbMock).getBook(book.id);
    }

    @Test
    public void getBookReturnsNullIfNotFound() throws TException
    {
        // Arrange
        when(dbMock.getBook(1234)).thenReturn(null);

        // Act
        Book result = handler.getBook(1234);

        // Assert
        assertNull("book should match", result);
        verify(dbMock).getBook(1234);
    }
}
