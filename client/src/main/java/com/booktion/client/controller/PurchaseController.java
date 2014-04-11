package com.booktion.client.controller;

import com.booktion.client.gui.JPurchaseDialog;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PurchaseController
{
    private MainController mainController;
    private JPurchaseDialog purchaseDialog;

    public PurchaseController(MainController mainController, JPurchaseDialog window)
    {
        this.mainController = mainController;
        this.purchaseDialog = window;

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
            mainController.getConnector().purchase(purchaseDialog.getAdvert().book);
        } catch (TException e) {}

        purchaseDialog.close();
    }

    private void onCancel()
    {
        purchaseDialog.close();
    }
}
