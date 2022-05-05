package waiter;

public class EchoProtocolMock implements Protocol {
    public Message serve(Message mockMessage) {
        return mockMessage;
    }
}
