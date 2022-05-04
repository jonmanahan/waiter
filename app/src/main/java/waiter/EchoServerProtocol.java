package waiter;

public class EchoServerProtocol implements Protocol{
    @Override
    public Message serve(Message fromClient) {
        return fromClient;
    }
}
