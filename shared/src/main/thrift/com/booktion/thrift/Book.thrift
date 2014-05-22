namespace java com.booktion.thrift

struct Book {
    1: i32 id,
    2: string title,
    3: string author,
    4: string publisher,
    5: string description,
    6: i16 yearOfPublication,
    7: i16 condition,
}
