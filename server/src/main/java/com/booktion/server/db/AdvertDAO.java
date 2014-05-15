package com.booktion.server.db;

import com.booktion.server.model.Advert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertDAO extends DAO
{
    private static final String SELECT_BY_ID = "SELECT * FROM adverts WHERE id = ?";

    private BookDAO book;

    public AdvertDAO(Connection connection, BookDAO bookDAO)
    {
        super(connection);

        book = bookDAO;
    }

    public Advert getAdvert(int advertId)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, advertId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Advert advert = new Advert(
                    result.getInt("id"),
                    result.getInt("issuer"),
                    book.getById(result.getInt("bookId")),
                    Advert.AdvertType.valueOf(result.getString("type")),
                    result.getDate("expires"),
                    result.getDouble("price"),
                    result.getInt("winner")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
