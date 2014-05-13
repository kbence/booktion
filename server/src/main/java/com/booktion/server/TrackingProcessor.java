package com.booktion.server;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TrackingProcessor implements TProcessor
{
    private TProcessor processor;
    private UserSessionManager sessionManager;

    public TrackingProcessor(TProcessor processor, UserSessionManager sessionManager)
    {
        this.processor = processor;
        this.sessionManager = sessionManager;
    }

    public boolean process(TProtocol in, TProtocol out) throws TException
    {
        boolean result;

        sessionManager.enterProcess(in.getTransport());
        result = this.processor.process(in, out);
        sessionManager.exitProcess();

        return result;
    }
}
