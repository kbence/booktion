package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.gui.MainWindow;
import com.booktion.log.Logger;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainController
{
    MainWindow window;
    BooktionConnector connector;

    public MainController(MainWindow mainWindow)
    {
        window = mainWindow;

        addListeners();
        connect();
    }

    private void addListeners()
    {
        window.getPingButton().addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ping();
            }
        });
    }

    private void connect()
    {
        try {
            if (connector == null) {
                connector = BooktionConnectorFactory.create();
                connector.connect("localhost", 1234);
                window.setResponseText("Connected");
            }
        } catch (TException e) {
            connector = null;
            window.setResponseText("Disconnected");
        }
    }

    private void ping()
    {
        try {
            connect();
            connector.ping();
        } catch (TException e) {
            window.setResponseText("Connection failed: " + e.getMessage());
        }
    }
}
