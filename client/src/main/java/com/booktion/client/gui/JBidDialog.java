package com.booktion.client.gui;

import com.booktion.thrift.Advert;

import javax.swing.*;

public class JBidDialog extends JActionDialog
{
    private final int GAP = 5;

    public JBidDialog(Advert advert)
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
