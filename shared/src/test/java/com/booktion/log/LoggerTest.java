package com.booktion.log;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintStream;

public class LoggerTest
{
    @Test
    public void pushPopGetShouldWorkCorrectly()
    {
        Logger original = Logger.get();
        Logger replacement = new Logger();
        Logger returned;

        Logger.push(replacement);
        Assert.assertNotEquals("loggers should be different", original, replacement);
        Assert.assertEquals("top logger should be ours", replacement, Logger.get());

        returned = Logger.pop();
        Assert.assertEquals("popped logger should be ours", replacement, returned);
        Assert.assertEquals("logger should be the original", original, Logger.get());
    }

    @Test
    public void getShouldThrowExceptionIfEmpty()
    {
        Logger original = Logger.pop();

        try {
            Logger.get();
            Assert.fail("it should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            Assert.assertEquals("No available loggers!", e.getMessage());
        }

        Logger.push(original);
    }

    @Test
    public void shouldLogWithDate()
    {
        StringOutputStream outputStream = new StringOutputStream();
        Logger logger = new Logger(new PrintStream(outputStream));
        DateUtil dateUtil = new DateUtil() {
            @Override
            public String getTimestamp() { return "2014-03-08 06:49:34"; }
        };

        logger.setDateUtil(dateUtil);
        logger.log("this is a log");

        String output = outputStream.toString();
        Assert.assertEquals("log should end with given text",
                "[2014-03-08 06:49:34] this is a log\n", output);
    }
}
