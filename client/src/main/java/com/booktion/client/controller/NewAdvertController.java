package com.booktion.client.controller;

import com.booktion.client.gui.JNewAdvertDialog;
import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;
import com.booktion.thrift.Book;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class NewAdvertController
{
    private MainController mainController;
    private JNewAdvertDialog newAdvertDialog;

    public NewAdvertController(MainController controller, JNewAdvertDialog window)
    {
        this.mainController = controller;
        this.newAdvertDialog = window;

        addListeners();
    }

    private void addListeners()
    {
        newAdvertDialog.getOkButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onOk();
            }
        });

        newAdvertDialog.getCancelButton().addActionListener(new AbstractAction()
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
        Book book = new Book();
        book.title = newAdvertDialog.getTitleTextField().getText();
        book.author = newAdvertDialog.getAuthorTextField().getText();
        book.publisher = newAdvertDialog.getPublisherTextField().getText();
        book.condition = (short)newAdvertDialog.getConditionSlider().getValue();

        try {
            AdvertType type = newAdvertDialog.getTypeComboBox().getSelectedIndex() == 0
                    ? AdvertType.FIX_PRICE : AdvertType.AUCTION;

            Date expires = newAdvertDialog.getDateTimePicker().getDateTime();

            boolean success = mainController.getConnector().createAdvert(book, type, expires.getTime(),
                    Double.valueOf(newAdvertDialog.getPriceTextField().getText()));

            if (success)
                    newAdvertDialog.close();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void onCancel()
    {
        newAdvertDialog.close();
    }
}
