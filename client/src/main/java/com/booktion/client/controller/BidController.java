package com.booktion.client.controller;

import com.booktion.client.gui.JBidWindow;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BidController
{
    private MainController mainController;
    private JBidWindow bidWindow;

    public BidController(MainController mainController, JBidWindow window)
    {
        this.mainController = mainController;
        this.bidWindow = window;

        addListeners(window);
    }

    private void addListeners(JBidWindow window)
    {
        window.getOkButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onOk();

            }
        });

        window.getCancelButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });
    }

    private void onOk()
    {
        try {
            mainController.getConnector().bid(bidWindow.getAdvert().book);
        } catch (TException e) {}

        closeWindow();
    }

    private void onCancel()
    {
        closeWindow();
    }

    private void closeWindow()
    {
        this.bidWindow.setVisible(false);
    }
}
