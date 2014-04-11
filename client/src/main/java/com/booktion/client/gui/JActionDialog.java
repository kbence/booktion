package com.booktion.client.gui;

import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JActionDialog extends JDialog
{
    protected final int GAP = 5;

    private Advert advert;

    private JButton okButton;
    private JButton cancelButton;

    public JActionDialog(Advert advert)
    {
        super();
        this.advert = advert;

        initComponents(advert);

        setModal(true);
        setResizable(false);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public Advert getAdvert()
    {
        return advert;
    }

    protected void initComponents(Advert advert)
    {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new GridBagLayout());

        int row = 0;
        addField(getTranslation("book.title"), advert.book.title, row++);
        addField(getTranslation("book.author"), advert.book.author, row++);
        addField(getTranslation("book.publisher"), advert.book.publisher, row++);
        addField(getTranslation("advert.type"), advert.advertType == AdvertType.FIX_PRICE ? "Fix áras" : "Aukció", row++);
        addField(getTranslation("advert.price"), String.format("%.0f", advert.price), row++);

        contentPane.add(new JLabel("Licit:"), cell(0, row));
        contentPane.add(new JTextField("0.0", 8), cell(1, row++));

        okButton = new JButton("OK");
        cancelButton = new JButton("Mégsem");

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, GAP, GAP));
        buttonPane.add(okButton);
        buttonPane.add(cancelButton);
        contentPane.add(buttonPane, cell(0, row++, 2, 1));

        contentPane.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
        pack();
    }

    protected String getTranslation(String id)
    {
        if (id.equals("book.title")) return "Cím";
        if (id.equals("book.author")) return "Szerző";
        if (id.equals("book.publisher")) return "Kiadó";
        if (id.equals("advert.type")) return "Típus";
        if (id.equals("advert.price")) return "Minimálár";

        return "-- UNKNOWN ID --";
    }

    private void addField(String field, String value, int row)
    {
        JPanel contentPane = (JPanel) getContentPane();
        JLabel valueLabel = new JLabel(value);

        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD));
        contentPane.add(new JLabel(field), cell(0, row));
        contentPane.add(valueLabel, cell(1, row));
    }

    private GridBagConstraints cell(int x, int y)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.ipadx = 3;
        c.ipady = 3;
        c.anchor = GridBagConstraints.WEST;

        return c;
    }

    private GridBagConstraints cell(int x, int y, int w, int h)
    {
        GridBagConstraints c = cell(x, y);
        c.gridwidth = w;
        c.gridheight = h;

        return c;
    }

    public JButton getOkButton()
    {
        return okButton;
    }

    public JButton getCancelButton()
    {
        return cancelButton;
    }
}