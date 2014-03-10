package com.booktion.client;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import org.apache.thrift.TException;

public class BooktionClient
{
    public void run()
    {
        System.out.println("Booktion client is starting up...");

        try {
            BooktionConnector connector = BooktionConnectorFactory.create();
            connector.connect("localhost", 1234);
            connector.ping();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new BooktionClient().run();
    }
}
