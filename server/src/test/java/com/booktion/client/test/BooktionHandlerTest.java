package com.booktion.client.test;

import com.booktion.log.Logger;
import com.booktion.util.StringOutputStream;
import com.booktion.client.BooktionHandler;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

public class BooktionHandlerTest
{
    StringOutputStream logOutputStream;
    @Before
    public void pushTestLogger()
    {
        logOutputStream = new StringOutputStream();
        Logger logger = new Logger(new PrintStream(logOutputStream));
        Logger.push(logger);
    }

    @After
    public void popTestLogger()
    {
        Logger.pop();
    }

    public void assertLog(String message, String logEntry)
    {
        String[] logLines = logOutputStream.toString().split("\n");

        for (String line : logLines) {
            if (line.substring(22).equals(logEntry)) {
                return;
            }
        }

        Assert.fail("Log entry \"" + logEntry + "\" has not been logged: " + message);
    }

    @Test
    public void echoShouldLogMessage() throws TException
    {
        BooktionHandler handler = new BooktionHandler();
        String message = "test message for echo";

        handler.echo(new Message(message));
        assertLog("should log the correct string", "CLIENT: " + message);
    }
}
