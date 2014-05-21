package com.booktion.server.db;

import com.booktion.server.model.Bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidDAO extends DAO
{
    private static final String GET_HIGHEST_PRICE = "SELECT MAX(price) AS price FROM bids WHERE advertId = ? ";
    private static final String PUT_BID = "INSERT INTO bids (userId, advertId, price) VALUES (?, ?, ?)";

    public BidDAO(Connection connection)
    {
        super(connection);
    }

    public boolean put(Bid bid)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(PUT_BID);
            stmt.setInt(1, bid.userId);
            stmt.setInt(2, bid.advertId);
            stmt.setDouble(3, bid.price);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public double getHighestPrice(int advertId)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(GET_HIGHEST_PRICE);
            stmt.setInt(1, advertId);
            ResultSet result = stmt.executeQuery();

            if (result.next())
                return result.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
