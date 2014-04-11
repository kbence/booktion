package com.booktion.client.controller;

import com.booktion.client.gui.JPurchaseWindow;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PurchaseController
{
    private MainController mainController;
    private JPurchaseWindow purchaseWindow;

    public PurchaseController(MainController mainController, JPurchaseWindow window)
    {
        this.mainController = mainController;
        this.purchaseWindow = window;

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
            mainController.getConnector().purchase(purchaseWindow.getAdvert().book);
        } catch (TException e) {}

        closeWindow();
    }

    private void onCancel()
    {
        closeWindow();
    }

    private void closeWindow()
    {
        this.purchaseWindow.setVisible(false);
    }
}
