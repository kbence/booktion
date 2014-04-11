package com.booktion.client.controller;

import com.booktion.client.gui.JNewAdvertDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAdvertController
{
    private JNewAdvertDialog window;

    public NewAdvertController(JNewAdvertDialog window)
    {
        this.window = window;

        addListeners();
    }

    private void addListeners()
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
        window.close();
    }

    private void onCancel()
    {
        window.close();
    }
}
