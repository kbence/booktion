package com.booktion.server.db;

import com.booktion.server.model.Advert;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class AdvertDAO extends DAO
{
    private static final String SELECT_BY_ID = "SELECT * FROM adverts WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM adverts WHERE winner IS NULL";
    private static final String FINALIZE_ADVERT = "UPDATE adverts SET winner = ? WHERE id = ?";
    private static final String INSERT_ADVERT = "INSERT INTO adverts (issuer, bookId, type, " +
            "expires, price) VALUES (?, ?, ?, ?, ?)";

    private BookDAO book;
    private BidDAO bid;

    public AdvertDAO(Connection connection, BookDAO bookDAO, BidDAO bidDAO)
    {
        super(connection);

        book = bookDAO;
        bid = bidDAO;
    }

    public Advert getById(int advertId)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, advertId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return createAdvertFromResult(result);
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

    public boolean create(Advert advert)
    {
        try {
            int bookId = book.create(advert.book);

            if (bookId < 0)
                return false;

            PreparedStatement stmt = connection.prepareStatement(INSERT_ADVERT);
            stmt.setInt(1, advert.issuer);
            stmt.setInt(2, bookId);
            stmt.setString(3, advert.type.toString());
            stmt.setLong(4, advert.expires.getTime());
            stmt.setInt(5, advert.winner);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Advert createAdvertFromResult(ResultSet result) throws SQLException
    {
        int advertId = result.getInt("id");
        double highestBid = bid.getHighestPrice(advertId);

        return new Advert(
            advertId,
            result.getInt("issuer"),
            book.getById(result.getInt("bookId")),
            Advert.AdvertType.valueOf(result.getString("type")),
            new Date(result.getInt("expires")),
            Math.max(result.getDouble("price"), highestBid),
            result.getInt("winner")
        );
    }
}
