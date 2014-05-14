package com.booktion.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JRegisterDialog extends JCenteredDialog
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField forenameField;
    private JTextField surnameField;
    private JTextField addressField;
    private JButton okButton;
    private JButton cancelButton;

    public JRegisterDialog()
    {
        setTitle("Regisztráció");

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
        forenameField = new JTextField(20);
        surnameField = new JTextField(20);
        addressField = new JTextField(20);

        int row = 1;
        addField(inputPanel, "Felhasználónév", usernameField, row++);
        addField(inputPanel, "Jelszó", passwordField, row++);
        addField(inputPanel, "Vezetéknév", surnameField, row++);
        addField(inputPanel, "Keresztnév", forenameField, row++);
        addField(inputPanel, "Cím", addressField, row++);

        return inputPanel;
    }

    private void addField(JPanel inputPanel, String fieldName, JTextField control, int row)
    {
        inputPanel.add(new JLabel(fieldName + ":"), cell(1, row));
        inputPanel.add(control, cell(2, row));
    }

    public JTextField getUsernameField()
    {
        return usernameField;
    }

    public JPasswordField getPasswordField()
    {
        return passwordField;
    }

    public JTextField getForenameField()
    {
        return forenameField;
    }

    public JTextField getSurnameField()
    {
        return surnameField;
    }

    public JTextField getAddressField()
    {
        return addressField;
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
