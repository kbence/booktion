package com.booktion.client.gui;

public class JCenteredDialog extends JGridBagDialog
{
    public JCenteredDialog()
    {
        super();

        centerDialog();
    }

    protected void centerDialog()
    {
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
