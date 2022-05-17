package waiter.EchoProtocol.mock;

import waiter.EchoProtocol.Protocol;

public class EchoProtocolMock implements Protocol {

    public String serve(String mockMessage) {
        return mockMessage;
    }
}

