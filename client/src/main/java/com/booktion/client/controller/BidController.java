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
        window.getPurchaseButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onOk();

            }
        });

        window.getCloseButton().addActionListener(new AbstractAction()
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
            boolean success = mainController.getConnector().bid(
                    bidDialog.getAdvert().id,
                    Double.valueOf(bidDialog.getPriceField().getText())
            );

            if (success) {
                JOptionPane.showMessageDialog(bidDialog, "Sikeres licitálás!", "Licitálás",
                        JOptionPane.INFORMATION_MESSAGE);

                closeWindow();
            } else {
                JOptionPane.showMessageDialog(bidDialog, "Sikertelen licitálás!", "Licitálás",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void onCancel()
    {
        closeWindow();
    }

    private void closeWindow()
    {
        mainController.refresh();
        bidDialog.close();
    }

}
