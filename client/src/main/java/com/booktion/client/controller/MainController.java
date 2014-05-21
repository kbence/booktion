package com.booktion.client.controller;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.BooktionConnectorFactory;
import com.booktion.client.gui.*;
import com.booktion.client.model.AdvertTableModel;
import com.booktion.client.model.BookTableModel;
import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;
import com.booktion.thrift.Book;
import org.apache.thrift.TException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainController
{
    public static final int ADVERT_LIST = 0;
    public static final int SEARCH = 1;
    public static final int OWN_PAGE = 2;

    JMainWindow window;
    private BooktionConnector connector;

    private AdvertTableModel advertListModel;
    private AdvertTableModel searchResultsModel;
    private BookTableModel ownBooksModel;
    private BookTableModel boughtBooksModel;
    private List<Advert> adverts;
    private List<Advert> searchResults;
    boolean logStatus;

    public MainController(JMainWindow mainWindow)
    {
        window = mainWindow;
        logStatus = false;

        setUpControls();
        addListeners();
        connect();
        loadAdvertList();
    }

    private void setUpControls()
    {
        advertListModel = new AdvertTableModel();
        searchResultsModel = new AdvertTableModel();
        ownBooksModel = new BookTableModel();
        boughtBooksModel = new BookTableModel();

        window.getAdvertList().getTable().setModel(advertListModel);
        window.getSearchResults().getTable().setModel(searchResultsModel);
        window.getOwnBooksTable().getTable().setModel(ownBooksModel);
        window.getBoughtBooksTable().getTable().setModel(boughtBooksModel);
    }

    private void addListeners()
    {
        window.getLoginButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showLoginWindows();
            }
        });

        window.getLogoutButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logout();
            }
        });

        window.getRegisterButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showRegisterWindow();
            }
        });

        window.getSearchButton().addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                searchForAdvert();
            }
        });

        window.getCreateAdvertButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showNewAdvertDialog();
            }
        });

        window.getAdvertList().getTable().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                onMouseClicked(e, adverts);
            }
        });

        window.getSearchResults().getTable().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                onMouseClicked(e, searchResults);
            }
        });

        window.getTabbedPane().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                refresh();
            }
        });
    }

    private void onMouseClicked(MouseEvent e, List<Advert> adverts)
    {
        if (!logStatus) return;

        if (e.getClickCount() == 2) {
            JTable table = (JTable)e.getSource();

            Advert advert = adverts.get(table.getSelectedRow());

            if (advert.advertType == AdvertType.AUCTION) {
                startBidding(advert);
            } else {
                startPurchase(advert);
            }
        }
    }

    private void showRegisterWindow()
    {
        JRegisterDialog registerDialog = new JRegisterDialog();
        new RegisterController(this, registerDialog);
        registerDialog.setVisible(true);
    }

    private void showLoginWindows()
    {
        JLoginDialog loginWindow = new JLoginDialog();
        new LoginController(this, loginWindow);
        loginWindow.setVisible(true);
    }

    private void logout()
    {
        try {
            connector.logout();
            setLogStatus(false);
        } catch (TException ex) {
            ex.printStackTrace();
        }
    }

    private void showNewAdvertDialog()
    {
        JNewAdvertDialog newAdvertWindow = new JNewAdvertDialog();
        new NewAdvertController(this, newAdvertWindow);
        newAdvertWindow.setVisible(true);
    }

    private void startBidding(Advert advert)
    {
        JBidDialog bidWindow = new JBidDialog(advert);
        new BidController(this, bidWindow);
        bidWindow.setVisible(true);
    }

    private void startPurchase(Advert advert)
    {
        JPurchaseDialog purchaseWindow = new JPurchaseDialog(advert);
        new PurchaseController(this, purchaseWindow);
        purchaseWindow.setVisible(true);
    }

    public void refresh()
    {
        switch (window.getTabbedPane().getSelectedIndex()) {
            case ADVERT_LIST: loadAdvertList(); break;
            case SEARCH: searchForAdvert(); break;
            case OWN_PAGE: refreshOwnPage(); break;
        }
    }

    private void loadAdvertList()
    {
        try {
            adverts = connector.listAdverts(0, 1000);
            advertListModel.setAdvertList(adverts);
            window.getAdvertList().invalidate();
        } catch (TException e) {
            window.getStatusLabel().setText("A kapcsolat megszakadt: " + e.getMessage());
        }
    }

    private void searchForAdvert()
    {
        try {
            searchResults = connector.searchForAdverts(window.getSearchTextField().getText());
            searchResultsModel.setAdvertList(searchResults);
            window.getSearchResults().invalidate();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void refreshOwnPage()
    {
        if (!logStatus) return;

        try {
            ownBooksModel.setAdvertList(connector.listOwnBooks());
            boughtBooksModel.setAdvertList(connector.listBoughtBooks());
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void connect()
    {
        int port = 1234;
        String host = "localhost";

        try {
            if (connector == null) {
                connector = BooktionConnectorFactory.create();
                connector.connect(host, port);
                window.getStatusLabel().setText("Kapcsolódva ide: " + host + ":" + port);
            }
        } catch (TException e) {
            connector = null;
            window.getStatusLabel().setText("Sikertelen kapcsolódás!");
        }
    }

    public void setLogStatus(boolean loggedIn)
    {
        logStatus = loggedIn;
        window.setLogStatus(loggedIn);
    }

    public BooktionConnector getConnector()
    {
        return connector;
    }
}
