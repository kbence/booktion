package com.booktion.client.gui;

import javax.swing.*;
import java.awt.*;

public class JTablePanel extends JPanel
{
    private JTable table;

    public JTablePanel()
    {
        initComponents();
    }

    private void initComponents()
    {
        table = new JTable();
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JTable getTable()
    {
        return table;
    }
}
