package com.booktion.server.db;

import com.booktion.server.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO extends DAO
{
    private final String INSERT_BOOK = "INSERT INTO BOOKS (title, owner, author, publisher, yearOfPublication, " +
            "condition, sold_to) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String SELECT_BOOK_BY_ID = "SELECT * FROM BOOKS WHERE id = ?";

        public BookDAO(Connection connection)
    {
        super(connection);
    }

    public boolean create(Book book)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_BOOK);
            stmt.setString(1, book.title);
            stmt.setInt(2, book.owner);
            stmt.setString(3, book.author);
            stmt.setString(4, book.publisher);
            stmt.setInt(5, book.yearOfPublication);
            stmt.setInt(6, book.condition);
            stmt.setObject(7, book.soldTo == -1 ? null : (Integer) book.soldTo);

            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Book getById(int id)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BOOK_BY_ID);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Book book = new Book();
                book.id = result.getInt("id");
                book.owner = result.getInt("owner");
                book.title = result.getString("author");
                book.author = result.getString("author");
                book.publisher = result.getString("publisher");
                book.yearOfPublication = result.getShort("yearOfPublication");
                book.condition = result.getShort("condition");
                book.soldTo = result.getInt("soldTo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
