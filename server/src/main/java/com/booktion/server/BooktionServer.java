package com.booktion.server;

import com.booktion.server.db.AdvertDatabase;
import com.booktion.thrift.BooktionService;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import java.sql.SQLException;

public class BooktionServer
{
    private BooktionService.Processor processor;

    public BooktionServer()
    {
        try {
            AdvertDatabase database = new AdvertDatabase(".booktion.derby");
            BooktionHandler booktionHandler = new BooktionHandler(database);
            processor = new BooktionService.Processor<BooktionHandler>(booktionHandler);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new RuntimeException();
        }
    }

    public void run()
    {
        System.out.println("Booktion server is starting up...");

        try {
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(1234);
            TNonblockingServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).processor(processor));

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
