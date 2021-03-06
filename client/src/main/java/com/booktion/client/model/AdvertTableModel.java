package com.booktion.client.model;

import com.booktion.thrift.Advert;
import com.booktion.thrift.AdvertType;
import com.booktion.thrift.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class AdvertTableModel extends AbstractTableModel
{
    private String[] columns;
    private List<Advert> adverts;

    public AdvertTableModel()
    {
        this.columns = new String[] {"Cím", "Szerző", "Kiadó", "Kiadás éve", "Típus", "Állapot",
                "Ár", "Hirdető"};
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
        return this.columns.length;
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
            case 3: return item.book.yearOfPublication;
            case 4: return item.advertType == AdvertType.FIX_PRICE ? "Fix áras" : "Aukció";
            case 5: return item.book.condition;
            case 6: return item.price;
            case 7: return item.issuer.username;
        }

        return null;
    }

    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }
}
