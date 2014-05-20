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
    private JTablePanel advertList;
    private JTablePanel searchResults;
    private JLabel statusLabel;
    private JButton createAdvertButton;
    private JTextField searchTextField;

    private JButton searchButton;

    public JMainWindow()
    {
        super();

        initContent();

        setTitle("Booktion kliens");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(800, 400));
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
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.add("Hirdetések", createAdvertListPanel());
        tabbedPane.add("Keresés", createSearchPanel());
        tabbedPane.add("Saját oldal", createOwnPanel());
    }

    private JPanel createOwnPanel()
    {
        createAdvertButton = new JButton("Hirdetés feladása");
        loggedInControls.add(createAdvertButton);

        JPanel ownPanel = new JPanel(new BorderLayout(5, 5));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, GAP, GAP));
        buttonPanel.add(createAdvertButton);

        ownPanel.add(buttonPanel, BorderLayout.NORTH);

        return ownPanel;
    }

    private JPanel createSearchPanel()
    {
        searchResults = new JTablePanel();
        searchTextField = new JTextField();
        searchButton = new JButton("Keresés");

        JPanel searchPanel = new JPanel();
        JPanel searchFieldPanel = new JPanel();

        searchFieldPanel.setLayout(new BorderLayout());
        searchFieldPanel.add(searchTextField, BorderLayout.CENTER);
        searchFieldPanel.add(searchButton, BorderLayout.EAST);
        searchPanel.setLayout(new BorderLayout(GAP, GAP));
        searchPanel.add(searchFieldPanel, BorderLayout.NORTH);
        searchPanel.add(searchResults, BorderLayout.CENTER);

        return searchPanel;
    }

    private JPanel createAdvertListPanel()
    {
        advertList = new JTablePanel();

        JPanel advertListPanel = new JPanel();
        advertListPanel.setLayout(new BorderLayout(GAP, GAP));
        advertListPanel.add(advertList, BorderLayout.CENTER);

        return advertListPanel;
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

    public JTablePanel getAdvertList()
    {
        return advertList;
    }

    public JButton getSearchButton()
    {
        return searchButton;
    }

    public JTextField getSearchTextField()
    {
        return searchTextField;
    }

    public JTablePanel getSearchResults()
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
