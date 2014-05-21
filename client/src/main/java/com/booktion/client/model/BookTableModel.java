package com.booktion.client.model;

import com.booktion.thrift.Advert;
import com.booktion.thrift.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel
{
    private String[] columns;
    private List<Book> books;

    public BookTableModel()
    {
        this.columns = new String[] {"Cím", "Szerző", "Kiadó", "Kiadás éve", "Állapot"};
    }

    public void setAdvertList(List<Book> bookList)
    {
        books = bookList;

        fireTableDataChanged();
    }

    @Override
    public int getRowCount()
    {
        if (books == null)
            return 0;

        return books.size();
    }

    @Override
    public int getColumnCount()
    {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (books == null)
            return null;

        Book item = books.get(rowIndex);

        switch (columnIndex) {
            case 0: return item.title;
            case 1: return item.author;
            case 2: return item.publisher;
            case 3: return item.yearOfPublication;
            case 4: return item.condition;
        }

        return null;
    }

    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }
}
