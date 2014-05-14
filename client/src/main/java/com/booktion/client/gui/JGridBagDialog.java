package com.booktion.client.gui;

import javax.swing.*;
import java.awt.*;

public class JGridBagDialog extends JDialog
{
    public JGridBagDialog()
    {
    }

    protected GridBagConstraints cell(int x, int y)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.ipadx = 3;
        c.ipady = 3;
        c.anchor = GridBagConstraints.WEST;

        return c;
    }

    protected GridBagConstraints cell(int x, int y, int anchor)
    {
        GridBagConstraints c = cell(x, y);
        c.anchor = anchor;
        return c;
    }

    protected GridBagConstraints cell(int x, int y, int w, int h)
    {
        GridBagConstraints c = cell(x, y);
        c.gridwidth = w;
        c.gridheight = h;

        return c;
    }

    protected GridBagConstraints cell(int x, int y, int w, int h, int anchor)
    {
        GridBagConstraints c = cell(x, y, w, h);
        c.anchor = anchor;

        return c;
    }

    public void close()
    {
        setVisible(false);
        dispose();
    }
}
