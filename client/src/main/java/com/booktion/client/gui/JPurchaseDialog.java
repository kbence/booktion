package com.booktion.client.gui;

import com.booktion.thrift.Advert;

import javax.swing.*;

public class JPurchaseDialog extends JActionDialog
{
    public JPurchaseDialog(Advert advert)
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

    @Override
    protected String getTranslation(String id)
    {
        if (id.equals("advert.price")) return "Ár";

        return super.getTranslation(id);
    }
}
