package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.gui.JBidWindow;
import com.booktion.client.gui.JMainWindow;
import com.booktion.client.gui.JNewAdvertWindow;
import com.booktion.client.model.AdvertTableModel;
import com.booktion.thrift.Advert;
import org.apache.thrift.TException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainController
{
    JMainWindow window;
    BooktionConnector connector;
    private AdvertTableModel advertListModel;
    private List<Advert> adverts;

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

        window.getAdvertList().getAdvertTable().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2) {
                    JTable table = (JTable)e.getSource();

                    int selectedRow = table.getSelectedRow();

                    JBidWindow bidWindow = new JBidWindow(adverts.get(selectedRow));
                    new BidController(bidWindow);
                    bidWindow.setVisible(true);
                }
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
        } catch (TException e) {
            window.getStatusLabel().setText("A kapcsolat megszakadt: " + e.getMessage());
        }
    }

    private void connect()
    {
        int port = 1234;
        String host = "localhost";

        try {
            if (connector == null) {
                connector = BooktionConnectorFactory.create();
                connector.connect(host, port);
                window.getStatusLabel().setText("Kapcsolódva ide: " + host + ":" + port);
            }
        } catch (TException e) {
            connector = null;
            window.getStatusLabel().setText("Sikertelen kapcsolódás!");
        }
    }
}
