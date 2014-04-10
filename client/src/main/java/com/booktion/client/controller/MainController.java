package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.gui.JMainWindow;
import com.booktion.client.gui.JNewAdvertWindow;
import org.apache.thrift.TException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        window.getCreateAdvertButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JNewAdvertWindow newAdvertWindow = new JNewAdvertWindow(window);
                new NewAdvertController(newAdvertWindow);
                newAdvertWindow.setVisible(true);
            }
        });
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
