package com.booktion.client.model;

import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AdvertTableModel extends AbstractTableModel
{
    private String[] columns;
    private List<Advert> adverts;

    public AdvertTableModel()
    {
        columns = new String[] {"Cím", "Szerző", "Kiadó", "Típus", "Ár"};
    }

    public void setAdvertList(List<Advert> advertList)
    {
        adverts = advertList;

        fireTableDataChanged();
    }

    @Override
    public int getRowCount()
    {
        if (adverts == null)
            return 0;

        return adverts.size();
    }

    @Override
    public int getColumnCount()
    {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (adverts == null)
            return null;

        Advert item = adverts.get(rowIndex);

        switch (columnIndex) {
            case 0: return item.book.title;
            case 1: return item.book.author;
            case 2: return item.book.publisher;
            case 3: return item.advertType == AdvertType.FIX_PRICE ? "Fix áras" : "Aukció";
            case 4: return item.price;
        }

        return null;
    }

    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }
}
