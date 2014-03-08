package com.booktion.server;

import com.booktion.log.Logger;
import com.booktion.thrift.BooktionService;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;

public class BooktionHandler implements BooktionService.Iface
{
    @Override
    public void echo(Message message) throws TException
    {
        Logger.get().log(message.getText());
    }
}
