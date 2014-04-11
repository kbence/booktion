package com.booktion.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JNewAdvertDialog extends JGridBagDialog
{
    private JTextField authorTextField;

    private JTextField titleTextField;
    private JTextField publisherTextField;
    private JTextArea descriptionTextArea;
    private JTextField priceTextField;
    private JComboBox<String> typeComboBox;

    private Button okButton;
    private Button cancelButton;

    public JNewAdvertDialog()
    {
        initComponents();
    }

    private void initComponents()
    {
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setLayout(new GridBagLayout());

        authorTextField = new JTextField(25);
        titleTextField = new JTextField(25);
        publisherTextField = new JTextField(25);
        descriptionTextArea = new JTextArea(4, 25);
        priceTextField = new JTextField(25);
        typeComboBox = new JComboBox<String>(new String[]{"Fix áras", "Aukció"});

        int row = 0;
        addField("Szerző:", authorTextField, row++);
        addField("Cím:", titleTextField, row++);
        addField("Kiadó:", publisherTextField, row++);
        addField("Leírás:", new JScrollPane(descriptionTextArea), row++);
        addField("Típus:", typeComboBox, row++);
        addField("Ár:", priceTextField, row++);

        okButton = new Button("OK");
        cancelButton = new Button("Mégsem");

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        buttonPane.add(okButton);
        buttonPane.add(cancelButton);
        contentPane.add(buttonPane, cell(0, row++, 2, 1, GridBagConstraints.WEST));

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        pack();
    }

    private void addField(String name, JComponent component, int row)
    {
        getContentPane().add(new JLabel(name), cell(0, row));
        getContentPane().add(component, cell(1, row));
    }

    private JLabel createLabel(String caption)
    {
        return new JLabel(caption);
    }

    public JTextField getAuthorTextField()
    {
        return authorTextField;
    }

    public JTextField getTitleTextField()
    {
        return titleTextField;
    }

    public JTextField getPublisherTextField()
    {
        return publisherTextField;
    }

    public JTextArea getDescriptionTextArea()
    {
        return descriptionTextArea;
    }

    public JTextField getPriceTextField()
    {
        return priceTextField;
    }

    public Button getOkButton()
    {
        return okButton;
    }

    public Button getCancelButton()
    {
        return cancelButton;
    }
}
