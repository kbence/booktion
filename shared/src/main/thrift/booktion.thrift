namespace java com.booktion.thrift

struct Message {
    1: string text
}

service BooktionService {
    void echo(1:Message message)
}
