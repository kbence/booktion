package com.booktion.server;

import com.booktion.thrift.BooktionService;
import com.booktion.thrift.Message;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class BooktionClient
{
    public void run()
    {
        System.out.println("Booktion client is starting up...");

        try {
            TTransport transport = new TSocket("localhost", 1234);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            BooktionService.Client client = new BooktionService.Client(protocol);
            Message message = client.echo(new Message("I am Client!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new BooktionClient().run();
    }
}
