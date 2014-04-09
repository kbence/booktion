package com.booktion.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow extends JDialog
{
    private final int GAP = 5;

    private JAdvertList advertList;
    private JAdvertList searchResults;
    private JLabel statusLabel;

    public MainWindow()
    {
        super();

        initContent();

        setTitle("Booktion client");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));

        System.gc();
    }

    private void initContent()
    {
        JPanel contentPane = (JPanel)getContentPane();

        statusLabel = new JLabel();
        advertList = new JAdvertList();
        searchResults = new JAdvertList();
        JButton createAdvert = new JButton("Hirdetés feladása");

        JPanel advertListPanel = new JPanel();
        advertListPanel.setLayout(new BorderLayout(GAP, GAP));
        advertListPanel.add(advertList, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout(GAP, GAP));
        searchPanel.add(searchResults);

        JPanel ownPanel = new JPanel();
        ownPanel.setLayout(new FlowLayout(FlowLayout.LEFT, GAP, GAP));
        ownPanel.add(createAdvert);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.add("Hirdetések", advertListPanel);
        tabbedPane.add("Keresés", searchPanel);
        tabbedPane.add("Saját oldal", ownPanel);

        contentPane.setLayout(new BorderLayout(GAP, GAP));
        contentPane.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        pack();
    }

    public JAdvertList getAdvertList()
    {
        return advertList;
    }

    public JAdvertList getSearchResults()
    {
        return searchResults;
    }

    public JLabel getStatusLabel()
    {
        return statusLabel;
    }
}
