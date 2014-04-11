package com.booktion.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JNewAdvertDialog extends JDialog
{
    private JTextField authorTextField;

    private JTextField titleTextField;
    private JTextField publisherTextField;
    private JTextArea descriptionTextArea;
    private JTextField priceTextField;

    private Button okButton;
    private Button cancelButton;

    public JNewAdvertDialog(JDialog owner)
    {
        super(owner);

        initComponents();

        setModal(true);
        setResizable(false);
        setLocationRelativeTo(owner);
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

        contentPane.add(authorLabel, singleCell(0, 0));
        contentPane.add(authorTextField, singleCell(1, 0));
        contentPane.add(titleLabel, singleCell(0, 1));
        contentPane.add(titleTextField, singleCell(1, 1));
        contentPane.add(publisherLabel, singleCell(0, 2));
        contentPane.add(publisherTextField, singleCell(1, 2));
        contentPane.add(descriptionLabel, singleCell(0, 3));
        contentPane.add(new JScrollPane(descriptionTextArea), singleCell(1, 3, GridBagConstraints.CENTER));
        contentPane.add(typeLabel, singleCell(0, 4));
        contentPane.add(typeComboBox, singleCell(1, 4));
        contentPane.add(priceLabel, singleCell(0, 5));
        contentPane.add(priceTextField, singleCell(1, 5));

        okButton = new Button("OK");
        cancelButton = new Button("Mégsem");

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        buttonPane.add(okButton);
        buttonPane.add(cancelButton);
        contentPane.add(buttonPane, multiCell(0, 5, 2, 1, GridBagConstraints.EAST));

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        pack();
    }

    private JLabel createLabel(String caption)
    {

        return new JLabel(caption);
    }

    private GridBagConstraints singleCell(int x, int y)
    {
        return singleCell(x, y, GridBagConstraints.WEST);
    }

    private GridBagConstraints singleCell(int x, int y, int anchor)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.anchor = anchor;

        return c;
    }

    private GridBagConstraints multiCell(int x, int y, int w, int h)
    {
        GridBagConstraints c = singleCell(x, y);
        c.gridwidth = w;
        c.gridheight = h;

        return c;
    }

    private GridBagConstraints multiCell(int x, int y, int w, int h, int anchor)
    {
        GridBagConstraints c = multiCell(x, y, w, h);
        c.anchor = anchor;

        return c;
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
