package com.booktion.client;

import com.booktion.log.Logger;
import com.booktion.thrift.BooktionService;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;

public class BooktionHandler implements BooktionService.Iface
{
    @Override
    public Message echo(Message message) throws TException
    {
        Logger.get().log("CLIENT: " + message.getText());

        return new Message("Server is up and running");
    }
}
