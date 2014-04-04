package com.booktion.client.test;

import com.booktion.log.util.LoggerTestCase;
import com.booktion.server.BooktionHandler;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;
import org.junit.Test;

public class BooktionHandlerTest extends LoggerTestCase
{
    @Test
    public void echoShouldLogMessage() throws TException
    {
        BooktionHandler handler = new BooktionHandler();
        String message = "test message for echo";

        handler.echo(new Message(message));
        assertLog("should log the correct string", "CLIENT: " + message);
    }
}
