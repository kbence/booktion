package com.booktion.server;

import com.booktion.thrift.BooktionService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class BooktionServer
{
    public BooktionService.Processor processor;

    public BooktionServer()
    {
        BooktionHandler booktionHandler = new BooktionHandler();
        processor = new BooktionService.Processor(booktionHandler);
    }

    public void run()
    {
        System.out.println("Booktion server is starting up...");

        try {
            TServerTransport serverTransport = new TServerSocket(1234);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new BooktionServer().run();
    }
}