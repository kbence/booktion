package com.booktion.client.gui;

import javax.swing.*;
import java.awt.*;

public class JAdvertList extends JPanel
{
    private JTable advertTable;

    public JAdvertList()
    {
        initComponents();
    }

    private void initComponents()
    {
        advertTable = new JTable();
        setLayout(new BorderLayout());
        add(new JScrollPane(advertTable), BorderLayout.CENTER);
    }
}
