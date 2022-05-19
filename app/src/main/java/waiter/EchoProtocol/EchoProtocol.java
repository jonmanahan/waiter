package waiter.EchoProtocol;

public class EchoProtocol implements Protocol {

    public String serve(String fromClient) {
        return fromClient;
    }
}

