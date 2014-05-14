package com.booktion.client.controller;

import com.booktion.client.gui.JRegisterDialog;
import com.booktion.thrift.User;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RegisterController
{
    private MainController mainController;
    private JRegisterDialog window;

    public RegisterController(MainController controller, JRegisterDialog registerWindow)
    {
        mainController = controller;
        window = registerWindow;

        addListeners();
    }

    private void addListeners()
    {
        window.getOkButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onOk();
            }
        });

        window.getCancelButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });
    }

    private void onOk()
    {
        try {
            User user = new User();
            user.username = window.getUsernameField().getText();
            user.password = String.valueOf(window.getPasswordField().getPassword());
            user.forename = window.getForenameField().getText();
            user.surname = window.getSurnameField().getText();
            user.address = window.getAddressField().getText();
            boolean success = mainController.getConnector().addUser(user);

            if (success) {
                window.close();
            } else {
                JOptionPane.showMessageDialog(window, "Hiba a felhasználó létrehozása közben!",
                        "Regisztráció", JOptionPane.ERROR_MESSAGE);
            }
        } catch (TException e) {
            e.printStackTrace();
        }

        window.close();
    }

    private void onCancel()
    {
        window.close();
    }
}
