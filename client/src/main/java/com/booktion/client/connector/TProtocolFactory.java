package com.booktion.client.connector;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by bnc on 3/8/14.
 */
public class TProtocolFactory
{
    public TProtocol createBinary(String host, int port) throws TException
    {
        TTransport transport = new TSocket(host, port);
        transport.open();

        return new TBinaryProtocol(transport);
    }
}
