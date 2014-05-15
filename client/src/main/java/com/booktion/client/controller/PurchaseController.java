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

        window.getPurchaseButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onPurchase();

            }
        });

        window.getCloseButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onClose();
            }
        });
    }

    private void onPurchase()
    {
        try {
            int answer = JOptionPane.showConfirmDialog(purchaseDialog,
                    "Biztosan szeretné megvásárolni ezt a könyvet?",
                    "Vásárlás", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (answer == JOptionPane.OK_OPTION) {
                boolean success = mainController.getConnector().purchase(purchaseDialog.getAdvert().book);

                if (success) {
                    JOptionPane.showMessageDialog(purchaseDialog, "A vásárlás sikeres volt!",
                            "Vásárlás", JOptionPane.INFORMATION_MESSAGE);
                    purchaseDialog.close();
                } else {
                    JOptionPane.showMessageDialog(purchaseDialog, "Hiba történt a vásárlás közben!",
                            "Vásárlás", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (TException e) {}
    }

    private void onClose()
    {
        purchaseDialog.close();
    }
}
