package com.booktion.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.transport.TTransport;

import java.util.HashMap;
import java.util.Map;

public class UserSessionManager
{
    private TProcessor processor;
    private Map<TTransport, UserSession> sessions;
    private UserSession currentSession;

    public UserSessionManager(TProcessor processor)
    {
        this.processor = new TrackingProcessor(processor, this);
        this.sessions = new HashMap<TTransport, UserSession>();
        this.currentSession = null;
    }

    public TProcessor getProcessor()
    {
        return processor;
    }

    public void enterProcess(TTransport transport)
    {
        if (!sessions.containsKey(transport)) {
            sessions.put(transport, new UserSession());
        }

        currentSession = sessions.get(transport);
    }

    public void exitProcess()
    {
        currentSession = null;
    }

    public UserSession getCurrentSession()
    {
        return currentSession;
    }

    public void deleteSession(int userId)
    {
        for (TTransport key : sessions.keySet()) {
            UserSession session = sessions.get(key);

            if (session.user.id == userId) {
                try {
                    sessions.remove(key);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
}
