package com.booktion.client;

import com.booktion.client.controller.MainController;
import com.booktion.client.gui.JMainWindow;

public class BooktionClient
{
    public void run()
    {
        JMainWindow window = new JMainWindow();
        window.setVisible(true);

        new MainController(window);
    }

    public static void main(String[] args)
    {
        new BooktionClient().run();
    }
}
