namespace java com.booktion.thrift

include "Book.thrift"

struct Bid {
    1: double price,
    2: Book.Book book,
}
