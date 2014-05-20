package com.booktion.server.db;

import com.booktion.server.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAO
{
    private final String INSERT_BOOK = "INSERT INTO books (title, owner, author, publisher, " +
            "yearOfPublication, condition) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";

        public BookDAO(Connection connection)
    {
        super(connection);
    }

    public int create(Book book)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, book.title);
            stmt.setInt(2, book.owner);
            stmt.setString(3, book.author);
            stmt.setString(4, book.publisher);
            stmt.setInt(5, book.yearOfPublication);
            stmt.setInt(6, book.condition);

            if (stmt.executeUpdate() == 0)
                return -1;

            ResultSet result = stmt.getGeneratedKeys();

            if (result.next())
                return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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
                book.title = result.getString("title");
                book.author = result.getString("author");
                book.publisher = result.getString("publisher");
                book.yearOfPublication = result.getShort("yearOfPublication");
                book.condition = result.getShort("condition");
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
