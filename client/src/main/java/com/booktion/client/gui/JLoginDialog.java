package com.booktion.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JLoginDialog extends JCenteredDialog
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;

    public JLoginDialog()
    {
        initControls();
        centerDialog();
    }

    private void initControls()
    {
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(createInputPanel());
        contentPane.add(createButtonPanel());
        pack();
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        okButton = new JButton("OK");
        cancelButton = new JButton("Mégsem");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private JPanel createInputPanel()
    {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        inputPanel.add(new JLabel("Felhasználónév:"), cell(1, 1));
        inputPanel.add(usernameField, cell(2, 1));
        inputPanel.add(new JLabel("Jelszó:"), cell(1, 2));
        inputPanel.add(passwordField, cell(2, 2));

        return inputPanel;
    }

    public JTextField getUsernameField()
    {
        return usernameField;
    }

    public JPasswordField getPasswordField()
    {
        return passwordField;
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
