namespace java com.booktion.thrift

include "User.thrift"
include "Book.thrift"
include "AdvertType.thrift"

struct Advert {
    1: i32 id,
    2: User.User issuer,
    3: Book.Book book,
    4: AdvertType.AdvertType advertType,
    5: i64 expires,
    6: double price,
    7: i32 winner
}
