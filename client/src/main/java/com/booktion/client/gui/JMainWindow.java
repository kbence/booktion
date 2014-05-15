package com.booktion.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class JMainWindow extends JDialog
{
    private final int GAP = 5;

    private JPanel buttonPanel;
    private JButton loginButton;

    private JButton logoutButton;
    private ArrayList<Component> loggedInControls;
    private ArrayList<Component> loggedOutControls;

    private JButton registerButton;

    private JTabbedPane tabbedPane;
    private JAdvertList advertList;
    private JAdvertList searchResults;
    private JLabel statusLabel;
    private JButton createAdvertButton;

    public JMainWindow()
    {
        super();

        initContent();

        setTitle("Booktion kliens");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);

        System.gc();
    }

    private void initContent()
    {
        loggedInControls = new ArrayList<Component>();
        loggedOutControls = new ArrayList<Component>();

        createButtonPanel();
        createAdvertPanel();

        statusLabel = new JLabel();

        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setLayout(new BorderLayout(GAP, GAP));
        contentPane.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        setLogStatus(false);
        pack();
    }

    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        loginButton = new JButton("Bejelentkezés");
        logoutButton = new JButton("Kijelentkezés");
        registerButton = new JButton("Regisztráció");

        loggedOutControls.add(loginButton);
        loggedInControls.add(logoutButton);
        loggedOutControls.add(registerButton);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        buttonPanel.add(loginButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(registerButton);
    }

    private void createAdvertPanel()
    {
        advertList = new JAdvertList();
        searchResults = new JAdvertList();
        createAdvertButton = new JButton("Hirdetés feladása");

        JPanel advertListPanel = new JPanel();
        advertListPanel.setLayout(new BorderLayout(GAP, GAP));
        advertListPanel.add(advertList, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout(GAP, GAP));
        searchPanel.add(searchResults);

        JPanel ownPanel = new JPanel();
        ownPanel.setLayout(new FlowLayout(FlowLayout.LEFT, GAP, GAP));
        ownPanel.add(createAdvertButton);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.add("Hirdetések", advertListPanel);
        tabbedPane.add("Keresés", searchPanel);
        tabbedPane.add("Saját oldal", ownPanel);
    }

    public void setLogStatus(boolean loggedIn)
    {
        for (Component control : loggedInControls) {
            control.setVisible(loggedIn);
        }

        for (Component control : loggedOutControls) {
            control.setVisible(!loggedIn);
        }
    }

    public JButton getLoginButton()
    {
        return loginButton;
    }

    public JButton getRegisterButton()
    {
        return registerButton;
    }

    public JButton getLogoutButton()
    {
        return logoutButton;
    }

    public JTabbedPane getTabbedPane()
    {
        return tabbedPane;
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

    public JButton getCreateAdvertButton()
    {
        return createAdvertButton;
    }
}
