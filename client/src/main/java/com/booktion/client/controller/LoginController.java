package com.booktion.client.controller;

import com.booktion.client.gui.JLoginDialog;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginController
{
    MainController mainController;
    JLoginDialog window;

    public LoginController(MainController controller, JLoginDialog loginWindow)
    {
        mainController = controller;
        window = loginWindow;

        addListeners(loginWindow);
    }

    private void addListeners(JLoginDialog loginWindow)
    {
        loginWindow.getOkButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onOk();
            }
        });

        loginWindow.getCancelButton().addActionListener(new AbstractAction()
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
            boolean loginSucceeded = mainController.getConnector().login(
                window.getUsernameField().getText(),
                String.valueOf(window.getPasswordField().getPassword())
            );

            if (loginSucceeded) {
                mainController.setLogStatus(true);
                window.close();
            } else {
                JOptionPane.showMessageDialog(window, "Sikertelen bejelentkezés",
                        "Bejelentkezés", JOptionPane.ERROR_MESSAGE);
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void onCancel()
    {
        window.close();
    }
}
