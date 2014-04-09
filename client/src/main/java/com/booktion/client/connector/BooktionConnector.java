package com.booktion.client.connector;

import com.booktion.thrift.BooktionService;
import org.apache.thrift.TException;

public class BooktionConnector
{
    private ClientFactory protocolFactory;
    private BooktionService.Client client;

    public BooktionConnector()
    {
        protocolFactory = new ClientFactory();
    }

    public void setProtocolFactory(ClientFactory factory)
    {
        protocolFactory = factory;
    }

    public void connect(String host, int port) throws TException
    {
        client = protocolFactory.createClient(host, port);
    }
}
