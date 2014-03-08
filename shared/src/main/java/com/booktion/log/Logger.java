package com.booktion.log;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Logger
{
    //
    // Static fields
    //

    private static List<Logger> loggers;

    //
    // Fields
    //

    private PrintStream printStream;
    private DateUtil dateUtil;

    //
    // Static methods
    //

    public static Logger get()
    {
        return getTopLogger();
    }

    private static List<Logger> getLoggers() {
        // lazy init
        if (loggers == null) {
            loggers = new LinkedList<Logger>();
            loggers.add(new Logger());
        }

        return loggers;
    }

    private static Logger getTopLogger() {
        if (getLoggers().isEmpty())
            throw new IndexOutOfBoundsException("No available loggers!");

        return loggers.get(0);
    }

    public static void push(Logger logger)
    {
        getLoggers().add(0, logger);
    }

    public static Logger pop()
    {
        Logger topLogger = getTopLogger();

        loggers.remove(0);

        return topLogger;
    }

    //
    // Methods
    //

    public Logger()
    {
        printStream = System.out;
        dateUtil = new DateUtil();
    }

    public Logger(PrintStream output)
    {
        this();
        printStream = output;
    }

    public void setDateUtil(DateUtil util)
    {
        dateUtil = util;
    }

    public void log(String message)
    {
        printStream.printf("[%s] %s\n", dateUtil.getTimestamp(), message);
    }
}
