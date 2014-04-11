package com.booktion.client.gui;

import com.booktion.thrift.Advert;

import javax.swing.*;

public class JBidWindow extends JActionDialog
{
    private final int GAP = 5;

    public JBidWindow(Advert advert)
    {
        super(advert);
    }

    @Override
    protected void initComponents(Advert advert)
    {
        super.initComponents(advert);

        JPanel contentPane = (JPanel) getContentPane();

        pack();
    }
}