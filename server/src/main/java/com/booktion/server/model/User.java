package com.booktion.server.model;

public class User
{
    private String userName;
    private String password;
    private String forename;
    private String surname;
    private String address;

    public User(String userName, String password, String forename, String surname, String address)
    {
        this.userName = userName;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    public boolean login(String password)
    {
        return password.equals(this.password);
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getForename()
    {
        return forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getAddress()
    {
        return address;
    }
}
