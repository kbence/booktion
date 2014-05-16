namespace java com.booktion.thrift

include "Book.thrift"

struct Bid {
    1: i32 advertId,
    2: i32 userId,
    3: double price,
}
