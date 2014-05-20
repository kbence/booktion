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

    public com.booktion.thrift.User toThrift()
    {
        com.booktion.thrift.User user = new com.booktion.thrift.User();
        user.username = username;
        user.forename = forename;
        user.surname = surname;
        user.address = address;

        return user;
    }

    public static User fromThrift(com.booktion.thrift.User thriftUser)
    {
        User user = new User();
        user.username = thriftUser.username;
        user.forename = thriftUser.forename;
        user.surname = thriftUser.surname;
        user.address = thriftUser.address;

        return user;
    }
}
