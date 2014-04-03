package com.booktion.client;

import com.booktion.client.controller.MainController;
import com.booktion.client.gui.MainWindow;

public class BooktionClient
{
    public void run()
    {
        System.out.println("Booktion client is starting up...");

        MainWindow window = new MainWindow();
        window.setVisible(true);

        new MainController(window);
    }

    public static void main(String[] args)
    {
        new BooktionClient().run();
    }
}
