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

        JLabel authorLabel = createLabel("Szerző:");
        JLabel titleLabel = createLabel("Cím:");
        JLabel publisherLabel = createLabel("Kiadó:");
        JLabel descriptionLabel = createLabel("Leírás:");
        JLabel priceLabel = createLabel("Ár:");
        JLabel typeLabel = createLabel("Típus:");
        authorTextField = new JTextField(25);
        titleTextField = new JTextField(25);
        publisherTextField = new JTextField(25);
        descriptionTextArea = new JTextArea(4, 25);
        priceTextField = new JTextField(25);
        JComboBox<String> typeComboBox = new JComboBox<String>(new String[]{"Fix áras", "Aukció"});

        contentPane.add(authorLabel, cell(0, 0));
        contentPane.add(authorTextField, cell(1, 0));
        contentPane.add(titleLabel, cell(0, 1));
        contentPane.add(titleTextField, cell(1, 1));
        contentPane.add(publisherLabel, cell(0, 2));
        contentPane.add(publisherTextField, cell(1, 2));
        contentPane.add(descriptionLabel, cell(0, 3));
        contentPane.add(new JScrollPane(descriptionTextArea), cell(1, 3, GridBagConstraints.CENTER));
        contentPane.add(typeLabel, cell(0, 4));
        contentPane.add(typeComboBox, cell(1, 4));
        contentPane.add(priceLabel, cell(0, 5));
        contentPane.add(priceTextField, cell(1, 5));

        okButton = new Button("OK");
        cancelButton = new Button("Mégsem");

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        buttonPane.add(okButton);
        buttonPane.add(cancelButton);
        contentPane.add(buttonPane, cell(0, 5, 2, 1, GridBagConstraints.EAST));

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        pack();
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
