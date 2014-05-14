package com.booktion.server.model;

public class User
{
    public int id;
    public String username;
    public String password;
    public String forename;
    public String surname;
    public String address;

    public User()
    {
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}
