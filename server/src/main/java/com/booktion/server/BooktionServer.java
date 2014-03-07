package com.booktion.server;

import com.booktion.thrift.Booktion;
import org.apache.thrift.server.TServerTransport;

public class BooktionServer
{
    public void run()
    {
        System.out.println("Booktion server is starting up...");

        try {
            TServerTransport serverTransport = new TServerSocket(1234);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new BooktionServer().run();
    }
}
