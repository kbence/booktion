namespace java com.booktion.thrift

struct Message {
    1: string text
}

service BooktionService {
    Message echo(1:Message message)
}
