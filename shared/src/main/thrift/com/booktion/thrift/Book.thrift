namespace java com.booktion.thrift

struct Book {
    1: i32 id,
    2: string title,
    3: string author,
    4: i32 publisherId,
    5: i16 yearOfPublication,
}
