package com.booktion.log.util;

import com.booktion.log.Logger;
import com.booktion.util.StringOutputStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.PrintStream;

/**
 * Created by bnc on 4/4/14.
 */
public class LoggerTestCase
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
}
