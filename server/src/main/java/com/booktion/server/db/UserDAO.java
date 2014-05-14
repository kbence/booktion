package com.booktion.server.db;

import com.booktion.log.Logger;
import com.booktion.server.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO
{
    private static final String SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String INSERT_USER = "INSERT INTO users (username, password, " +
            "forename, surname, address) VALUES (?, ?, ?, ?, ?)";

    public UserDAO(Connection connection)
    {
        super(connection);
    }

    public User findByUsername(String username)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_USERNAME);
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                User user = new User();
                user.id = result.getInt("id");
                user.username = result.getString("username");
                user.password = result.getString("password");
                user.forename = result.getString("forename");
                user.surname = result.getString("surname");
                user.address = result.getString("address");
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean createUser(User user)
    {
        if (findByUsername(user.username) != null)
            return false;

        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_USER);
            stmt.setString(1, user.username);
            stmt.setString(2, user.password);
            stmt.setString(3, user.forename);
            stmt.setString(4, user.surname);
            stmt.setString(5, user.address);
            stmt.execute();

            return stmt.getUpdateCount() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
