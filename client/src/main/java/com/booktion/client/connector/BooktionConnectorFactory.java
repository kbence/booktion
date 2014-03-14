package com.booktion.client.connector;

public class BooktionConnectorFactory
{
    public static BooktionConnector create()
    {
        BooktionConnector connector = new BooktionConnector();
        connector.setProtocolFactory(new ProtocolFactory());

        return connector;
    }
}
