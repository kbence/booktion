package com.booktion.server.model;

import java.util.Date;

public class Bid
{
    private double price;
    private Date time;

    public Bid(double price, Date time)
    {
        this.price = price;
        this.time = time;
    }

    public double getPrice()
    {
        return price;
    }

    public Date getTime()
    {
        return time;
    }
}
