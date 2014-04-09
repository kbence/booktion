package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.gui.JMainWindow;
import org.apache.thrift.TException;

public class MainController
{
    JMainWindow window;
    BooktionConnector connector;

    public MainController(JMainWindow mainWindow)
    {
        window = mainWindow;

        addListeners();
        connect();
    }

    private void addListeners()
    {
    }

    private void connect()
    {
        try {
            if (connector == null) {
                connector = BooktionConnectorFactory.create();
                connector.connect("localhost", 1234);
                window.getStatusLabel().setText("Connected");
            }
        } catch (TException e) {
            connector = null;
            window.getStatusLabel().setText("Disconnected");
        }
    }
}
