package waiter.EchoProtocol.mock;

import waiter.Message;
import waiter.EchoProtocol.Protocol;

public class EchoProtocolMock implements Protocol {
    public Message serve(Message mockMessage) {
        return mockMessage;
    }
}
