package com.booktion.client.controller;

import com.booktion.client.gui.JBidDialog;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BidController
{
    private MainController mainController;
    private JBidDialog bidDialog;

    public BidController(MainController mainController, JBidDialog window)
    {
        this.mainController = mainController;
        this.bidDialog = window;

        addListeners(window);
    }

    private void addListeners(JBidDialog window)
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
            mainController.getConnector().bid(bidDialog.getAdvert().book);
        } catch (TException e) {}

        bidDialog.close();
    }

    private void onCancel()
    {
        bidDialog.close();
    }

}
