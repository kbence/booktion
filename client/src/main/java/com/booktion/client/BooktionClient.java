package com.booktion.client;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.controller.MainController;
import com.booktion.client.gui.MainWindow;
import org.apache.thrift.TException;

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
