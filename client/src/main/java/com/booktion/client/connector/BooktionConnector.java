package com.booktion.client.connector;

import com.booktion.log.Logger;
import com.booktion.thrift.BooktionService;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class BooktionConnector
{
    private ProtocolFactory protocolFactory;
    private BooktionService.Client client;

    BooktionConnector()
    {
    }

    public void setProtocolFactory(ProtocolFactory factory)
    {
        protocolFactory = factory;
    }

    public void connect(String host, int port) throws TException
    {
        TProtocol protocol = protocolFactory.createBinary(host, port);
        client = new BooktionService.Client(protocol);
    }

    public void ping() throws TException
    {
        Message message = client.echo(new Message("PING"));
        Logger.get().log("SERVER: " + message.getText());
    }

}
