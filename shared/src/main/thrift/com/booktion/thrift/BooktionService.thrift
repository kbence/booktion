namespace java com.booktion.thrift

include "Advert.thrift"
include "Book.thrift"
include "Message.thrift"
include "Publisher.thrift"

service BooktionService {
    Message.Message echo(1:Message.Message message),

    Book.Book getBook(1:i32 bookId),

    bool addBook(1:Book.Book book),

    Publisher.Publisher getPublisher(1:i32 publisherId),

    list<Advert.Advert> searchForAdverts(1:string name),

    list<Advert.Advert> listAdverts(1:i32 first, 2:i32 last),
}
