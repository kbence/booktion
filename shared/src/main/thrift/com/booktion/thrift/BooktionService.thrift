namespace java com.booktion.thrift

include "Advert.thrift"
include "AdvertType.thrift"
include "Book.thrift"
include "User.thrift"

service BooktionService {
    bool login(1:string username, 2:string password),

    void logout(),

    bool addUser(1:User.User user),

    Book.Book getBook(1:i32 bookId),

    i32 addBook(1:Book.Book book),

    bool createAdvert(1:Book.Book book, 2:AdvertType.AdvertType advert, 3:i64 expires, 4:double price);

    list<Advert.Advert> searchForAdverts(1:string name),

    list<Advert.Advert> listAdverts(1:i32 first, 2:i32 last),

    list<Book.Book> listOwnBooks(),

    list<Book.Book> listBoughtBooks(),

    bool purchase(1:i32 advertId),

    bool bid(1:i32 advertId, 2:double price),
}
