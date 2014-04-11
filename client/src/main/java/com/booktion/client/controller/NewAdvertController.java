package com.booktion.client.controller;

import com.booktion.client.gui.JNewAdvertDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAdvertController
{
    private JNewAdvertDialog newAdvertDialog;

    public NewAdvertController(JNewAdvertDialog window)
    {
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
        newAdvertDialog.close();
    }

    private void onCancel()
    {
        newAdvertDialog.close();
    }
}
