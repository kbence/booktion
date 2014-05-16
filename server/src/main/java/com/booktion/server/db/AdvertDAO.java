package com.booktion.server.db;

import com.booktion.server.model.Advert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertDAO extends DAO
{
    private static final String SELECT_BY_ID = "SELECT * FROM adverts WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM adverts WHERE winner IS NULL";
    private static final String FINALIZE_ADVERT = "UPDATE adverts SET winner = ? WHERE id = ?";

    private BookDAO book;

    public AdvertDAO(Connection connection, BookDAO bookDAO)
    {
        super(connection);

        book = bookDAO;
    }

    public Advert getById(int advertId)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, advertId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Advert advert = createAdvertFromResult(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Advert> list()
    {
        ArrayList<Advert> adverts = new ArrayList<Advert>();

        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                adverts.add(createAdvertFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adverts;
    }

    public boolean finalize(int advertId, int userId)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(FINALIZE_ADVERT);
            stmt.setInt(1, userId);
            stmt.setInt(2, advertId);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Advert createAdvertFromResult(ResultSet result) throws SQLException
    {
        return new Advert(
            result.getInt("id"),
            result.getInt("issuer"),
            book.getById(result.getInt("bookId")),
            Advert.AdvertType.valueOf(result.getString("type")),
            result.getDate("expires"),
            result.getDouble("price"),
            result.getInt("winner")
        );
    }
}
