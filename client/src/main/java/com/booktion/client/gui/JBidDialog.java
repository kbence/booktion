package com.booktion.client.gui;

import com.booktion.thrift.Advert;

import javax.swing.*;

public class JBidDialog extends JActionDialog
{
    private final int GAP = 5;

    private JTextField priceField;

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

    @Override
    protected void initCustomComponents()
    {
        super.initCustomComponents();
        JPanel contentPane = (JPanel)getContentPane();

        priceField = new JTextField("0.0", 8);
        contentPane.add(new JLabel("Licit:"), cell(0, componentRow));
        contentPane.add(priceField, cell(1, componentRow++));
    }

    public JTextField getPriceField()
    {
        return priceField;
    }
}
