package com.booktion.server.db;

import com.booktion.server.model.Advert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertDAO extends DAO
{
    private static final String SELECT_BY_ID = "SELECT * FROM adverts WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM adverts WHERE winner IS NULL";
    private static final String SEARCH_FOR_BOOK = "SELECT adverts.* FROM adverts JOIN books " +
            "ON adverts.bookId = books.id WHERE books.title LIKE ? OR " +
            "books.author LIKE ? OR books.publisher LIKE ?";
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

    public List<Advert> searchForAdverts(String keyword)
    {
        List<Advert> adverts = new ArrayList<Advert>();

        try {
            String searchString = "%" + keyword + "%";
            PreparedStatement stmt = connection.prepareStatement(SEARCH_FOR_BOOK);

            stmt.setString(1, searchString);
            stmt.setString(2, searchString);
            stmt.setString(3, searchString);
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
            stmt.setDate(4, new java.sql.Date(advert.expires.getTime()));
            stmt.setInt(5, advert.winner);

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

        Date expires;

        try {
            expires = df.parse(result.getString("expires"));
        } catch (ParseException e) {
            expires = new Date(0);
        }

        return new Advert(
            advertId,
            result.getInt("issuer"),
            book.getById(result.getInt("bookId")),
            Advert.AdvertType.valueOf(result.getString("type")),
            expires,
            Math.max(result.getDouble("price"), highestBid),
            result.getInt("winner")
        );
    }
}
