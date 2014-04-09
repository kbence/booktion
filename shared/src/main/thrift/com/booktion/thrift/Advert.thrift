namespace java com.booktion.thrift

include "Book.thrift"
include "AdvertType.thrift"

struct Advert {
    1: i32 id,
    2: i32 issuer,
    3: Book.Book book,
    4: AdvertType.AdvertType advertType,
    5: double price
}
