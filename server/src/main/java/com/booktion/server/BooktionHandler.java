package com.booktion.server;

import com.booktion.thrift.Booktion;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;

public class BooktionHandler implements Booktion.Iface
{
    @Override
    public void echo(Message message) throws TException
    {
        System.out.println(message.getText());
    }
}
