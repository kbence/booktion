package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.gui.JMainWindow;
import com.booktion.client.gui.JNewAdvertWindow;
import com.booktion.client.model.AdvertTableModel;
import com.booktion.thrift.Advert;
import org.apache.thrift.TException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainController
{
    JMainWindow window;
    BooktionConnector connector;
    private List<Advert> adverts;
    private AdvertTableModel advertListModel;

    public MainController(JMainWindow mainWindow)
    {
        window = mainWindow;

        setUpControllers();
        addListeners();
        connect();
        loadAdvertList();
    }

    private void setUpControllers()
    {
        advertListModel = new AdvertTableModel();
        window.getAdvertList().getAdvertTable().setModel(advertListModel);
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

        window.getTabbedPane().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                if (((JTabbedPane)e.getSource()).getSelectedIndex() == 0) {
                    loadAdvertList();
                }
            }
        });
    }

    private void loadAdvertList()
    {
        try {
            adverts = connector.listAdverts(0, 1000);
            advertListModel.setAdvertList(adverts);
            window.getAdvertList().invalidate();
        } catch (TException e) {}
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
