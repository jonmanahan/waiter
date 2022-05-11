package waiter.EchoProtocol;

import waiter.Message;

public class EchoProtocol implements Protocol {

    public Message serve(Message fromClient) {
        return fromClient;
    }
}
