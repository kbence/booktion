package com.booktion.client.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JDialog
{
    private JButton pingButton;
    private JLabel responseLabel;

    public MainWindow()
    {
        super();

        initContent();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        System.gc();
    }

    private void initContent()
    {
        Container contentPane = getContentPane();
        FlowLayout mgr = new FlowLayout(FlowLayout.LEFT, 5, 5);

        setTitle("Booktion client");

        pingButton = new JButton("Ping Server");
        responseLabel = new JLabel();

        contentPane.setLayout(mgr);
        contentPane.add(pingButton);
        contentPane.add(responseLabel);
    }

    public JButton getPingButton()
    {
        return pingButton;
    }

    public void setResponseText(String message)
    {
        responseLabel.setText(message);
        pack();
    }
}
