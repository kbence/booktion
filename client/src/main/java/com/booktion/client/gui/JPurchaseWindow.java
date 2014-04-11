package com.booktion.client.gui;

import com.booktion.thrift.Advert;

import javax.swing.*;

public class JPurchaseWindow extends JActionDialog
{
    public JPurchaseWindow(Advert advert)
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
