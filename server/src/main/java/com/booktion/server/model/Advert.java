package com.booktion.server.model;

import java.util.Date;

public class Advert
{
    public enum AdvertType {
        FIX_PRICE,
        AUCTION;

        public com.booktion.thrift.AdvertType toThrift()
        {
            switch (this) {
                case AUCTION: return com.booktion.thrift.AdvertType.AUCTION;
                case FIX_PRICE: return com.booktion.thrift.AdvertType.FIX_PRICE;
            }

            return null;
        }

        public static AdvertType fromThrift(com.booktion.thrift.AdvertType type)
        {
            switch (type) {
                case AUCTION: return AdvertType.AUCTION;
                case FIX_PRICE: return AdvertType.FIX_PRICE;
            }

            return null;
        }
    }

    int id;
    int issuer;
    Book book;
    AdvertType type;
    Date expires;
    double price;
    int winner;

    public Advert(int id, int issuer, Book book, AdvertType type, Date expires, double price, int winner)
    {
        this.id = id;
        this.issuer = issuer;
        this.book = book;
        this.type = type;
        this.expires = expires;
        this.price = price;
        this.winner = winner;
    }

    public boolean bid(Bid bid)
    {
        // TODO: implement bidding logic
        return true;
    }

    public com.booktion.thrift.Advert toThrift()
    {
        return new com.booktion.thrift.Advert(id, issuer, book.toThriftBook(), type.toThrift(), expires.getTime(), price, winner);
    }

    public static Advert fromThrift(com.booktion.thrift.Advert advert)
    {
        return new Advert(advert.id, advert.issuer, Book.fromThriftBook(advert.book), AdvertType.fromThrift(advert.advertType), new Date(advert.expires), advert.price, advert.winner);
    }
}
