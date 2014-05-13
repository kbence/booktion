package com.booktion.server.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchemaCreator
{
    private Connection connection;

    public SchemaCreator(Connection connection)
    {
        this.connection = connection;
    }

    public boolean createSchema()
    {
        if (isUsersTableExist())
            return true;

        String schema = getSchemaSQL();

        if (schema == null)
            return false;

        return runSQLScript(schema);
    }

    private boolean runSQLScript(String schema)
    {
        String currentQuery = null;

        try {
            Statement stmt = connection.createStatement();

            for (String query : schema.split(";")) {
                if (query.trim().length() > 0) {
                    currentQuery = query;
                    stmt.execute(query);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error:");
            System.err.println(e.getMessage());
            System.err.println(addLineNumbers(currentQuery));
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String addLineNumbers(String text)
    {
        String newText = "";
        int currentLine = 0;

        for (String line : text.split("\n")) {
            if (currentLine++ > 0)
                newText += "\n";

            newText += String.format("%d: %s", currentLine, line);
        }

        return newText;
    }

    private String getSchemaSQL()
    {
        InputStream inputStream = getClass().getResourceAsStream("/db/schema/schema.sql");
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        String schema = "";
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                schema += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return schema;
    }

    private boolean isUsersTableExist()
    {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, "USERS", new String[] {"TABLE"});

            if (result.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
