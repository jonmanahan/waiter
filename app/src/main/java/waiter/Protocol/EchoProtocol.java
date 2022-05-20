package waiter.Protocol;

public class EchoProtocol implements Protocol {

    public String serve(String fromClient) {
        return fromClient;
    }
}

