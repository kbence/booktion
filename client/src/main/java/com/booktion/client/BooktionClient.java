package com.booktion.client;

import com.booktion.client.controller.MainController;
import com.booktion.client.gui.JMainWindow;

import javax.swing.*;

public class BooktionClient
{
    public void run()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                setLookAndFeel();

                JMainWindow window = new JMainWindow();
                window.setVisible(true);

                new MainController(window);
            }
        });
    }

    private void setLookAndFeel()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        new BooktionClient().run();
    }
}
