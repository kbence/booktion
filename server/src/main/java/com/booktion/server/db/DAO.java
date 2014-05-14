package com.booktion.server.db;

import java.sql.Connection;

public class DAO
{
    protected Connection connection;

    public DAO(Connection connection)
    {
        this.connection = connection;
    }
}
