package com.booktion.server.model;

public class Advert
{
    enum AdvertType {
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
    double price;

    public Advert(int id, int issuer, Book book, AdvertType type, double price)
    {
        this.id = id;
        this.issuer = issuer;
        this.book = book;
        this.type = type;
        this.price = price;
    }

    public com.booktion.thrift.Advert toThrift()
    {
        return new com.booktion.thrift.Advert(id, issuer, book.toThriftBook(), type.toThrift(), price);
    }

    public static Advert fromThrift(com.booktion.thrift.Advert advert)
    {
        return new Advert(advert.id, advert.issuer, Book.fromThriftBook(advert.book), AdvertType.fromThrift(advert.advertType), advert.price);
    }

    public Book getBook()
    {
        return book;
    }
}
