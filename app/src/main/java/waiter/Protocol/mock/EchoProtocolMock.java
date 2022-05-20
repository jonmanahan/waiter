package waiter.Protocol.mock;

import waiter.Protocol.Protocol;

public class EchoProtocolMock implements Protocol {

    public String serve(String mockMessage) {
        return mockMessage;
    }
}

