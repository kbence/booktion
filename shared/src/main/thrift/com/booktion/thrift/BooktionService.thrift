namespace java com.booktion.thrift

include "Message.thrift"

service BooktionService {
    Message.Message echo(1:Message.Message message)
}
