package com.booktion.server.db;

import com.booktion.server.model.Advert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertDAO extends DAO
{
    private static final String NOT_SOLD = "(type = 'FIX_PRICE' AND winner IS NULL OR " +
            "type = 'AUCTION' AND expires > ?)";
    private static final String NOT_EXPIRED = "(expires < ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM adverts WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM adverts WHERE " + NOT_SOLD +
            " AND " + NOT_EXPIRED;
    private static final String SELECT_BY_WINNER = "SELECT * FROM adverts WHERE winner = ? AND " +
            "(type = 'FIX_PRICE' OR expires < ?)";
    private static final String SEARCH_FOR_BOOK = "SELECT adverts.* FROM adverts JOIN books " +
            "ON adverts.bookId = books.id WHERE (books.title LIKE ? OR " +
            "books.author LIKE ? OR books.publisher LIKE ?) AND " + NOT_SOLD + " AND " + NOT_EXPIRED;
    private static final String SET_WINNER = "UPDATE adverts SET winner = ? WHERE id = ?";
    private static final String INSERT_ADVERT = "INSERT INTO adverts (issuer, bookId, type, " +
            "expires, price) VALUES (?, ?, ?, ?, ?)";

    private BookDAO book;
    private UserDAO user;
    private BidDAO bid;

    public AdvertDAO(Connection connection, UserDAO userDAO, BookDAO bookDAO, BidDAO bidDAO)
    {
        super(connection);

        book = bookDAO;
        user = userDAO;
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
            long time = new Date().getTime();

            stmt.setLong(1, time);
            stmt.setLong(2, time);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                adverts.add(createAdvertFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adverts;
    }

    public List<Advert> listByWinner(int winnerId)
    {
        ArrayList<Advert> adverts = new ArrayList<Advert>();

        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_WINNER);
            stmt.setInt(1, winnerId);
            stmt.setLong(2, new Date().getTime());
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                adverts.add(createAdvertFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adverts;
    }

    public List<Advert> searchForAdverts(String keyword)
    {
        List<Advert> adverts = new ArrayList<Advert>();

        try {
            String searchString = "%" + keyword + "%";
            PreparedStatement stmt = connection.prepareStatement(SEARCH_FOR_BOOK);
            long time = new Date().getTime();

            stmt.setString(1, searchString);
            stmt.setString(2, searchString);
            stmt.setString(3, searchString);
            stmt.setLong(4, time);
            stmt.setLong(5, time);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                adverts.add(createAdvertFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adverts;
    }

    public boolean setWinner(int advertId, int userId)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(SET_WINNER);
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
            stmt.setInt(1, advert.issuer.id);
            stmt.setInt(2, bookId);
            stmt.setString(3, advert.type.toString());
            stmt.setDate(4, new java.sql.Date(advert.expires.getTime()));
            stmt.setDouble(5, advert.price);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Advert createAdvertFromResult(ResultSet result) throws SQLException
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int advertId = result.getInt("id");
        double highestBid = bid.getHighestPrice(advertId);

        return new Advert(
            advertId,
            user.getById(result.getInt("issuer")),
            book.getById(result.getInt("bookId")),
            Advert.AdvertType.valueOf(result.getString("type")),
            new Date(result.getLong("expires")),
            Math.max(result.getDouble("price"), highestBid),
            result.getInt("winner")
        );
    }
}
