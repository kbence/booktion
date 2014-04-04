package com.booktion.client.connector;

import com.booktion.thrift.BooktionService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ClientFactory
{
    public BooktionService.Client createClient(String host, int port) throws TException
    {
        TTransport transport = new TSocket(host, port);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);

        return new BooktionService.Client(protocol);
    }
}
