package com.booktion.client.gui;

import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JActionDialog extends JCenteredDialog
{
    protected final int GAP = 5;

    private Advert advert;

    private JButton purchaseButton;
    private JButton closeButton;
    protected int componentRow;

    public JActionDialog(Advert advert)
    {
        super();
        this.advert = advert;

        initComponents(advert);
        centerDialog();
    }

    public Advert getAdvert()
    {
        return advert;
    }

    protected void initComponents(Advert advert)
    {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new GridBagLayout());

        componentRow = 0;
        addField(getTranslation("book.title"), advert.book.title, componentRow++);
        addField(getTranslation("book.author"), advert.book.author, componentRow++);
        addField(getTranslation("book.publisher"), advert.book.publisher, componentRow++);
        addField(getTranslation("advert.type"), advert.advertType == AdvertType.FIX_PRICE ? "Fix áras" : "Aukció", componentRow++);
        addField(getTranslation("advert.price"), String.format("%.0f", advert.price), componentRow++);

        initCustomComponents();

        purchaseButton = new JButton(getTranslation("action.purchase"));
        closeButton = new JButton(getTranslation("action.close"));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, GAP, GAP));
        buttonPane.add(purchaseButton);
        buttonPane.add(closeButton);

        contentPane.add(buttonPane, cell(0, componentRow++, 2, 1));
        contentPane.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
        pack();
    }

    protected void initCustomComponents()
    {
    }

    protected String getTranslation(String id)
    {
        if (id.equals("book.title")) return "Cím";
        if (id.equals("book.author")) return "Szerző";
        if (id.equals("book.publisher")) return "Kiadó";
        if (id.equals("advert.type")) return "Típus";
        if (id.equals("advert.price")) return "Minimálár";
        if (id.equals("action.purchase")) return "LICITÁLÁS";
        if (id.equals("action.close")) return "Bezárás";

        return "-- UNKNOWN ID --";
    }

    protected void addField(String field, String value, int row)
    {
        JPanel contentPane = (JPanel) getContentPane();
        JLabel valueLabel = new JLabel(value);

        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD));
        contentPane.add(new JLabel(field + ":"), cell(0, row));
        contentPane.add(valueLabel, cell(1, row));
    }

    public JButton getPurchaseButton()
    {
        return purchaseButton;
    }

    public JButton getCloseButton()
    {
        return closeButton;
    }
}
