namespace java com.booktion.thrift

include "Advert.thrift"
include "Book.thrift"

service BooktionService {
    Book.Book getBook(1:i32 bookId),

    bool addBook(1:Book.Book book),

    list<Advert.Advert> searchForAdverts(1:string name),

    list<Advert.Advert> listAdverts(1:i32 first, 2:i32 last),

    bool purchase(1:i32 bookId),

    bool bid(1:i32 bookId),
}
