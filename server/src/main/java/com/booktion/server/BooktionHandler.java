package com.booktion.server;

import com.booktion.thrift.BooktionService;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BooktionHandler implements BooktionService.Iface
{
    @Override
    public void echo(Message message) throws TException
    {
        System.out.println(String.format("%s %s", getTimestampString(), message.getText()));
    }

    private String getTimestampString() {
        Calendar calendar = new GregorianCalendar();

        return String.format(
                "[%04d-%02d-%02d %02d:%02d:%02d]",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
    }
}
