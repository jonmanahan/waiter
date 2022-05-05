package waiter;

public class EchoProtocol implements Protocol {
    public Message serve(Message fromClient) {
        return fromClient;
    }
}
