package com.booktion.client;

import com.booktion.client.controller.MainController;
import com.booktion.client.gui.JMainWindow;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

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
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        new BooktionClient().run();
    }
}
