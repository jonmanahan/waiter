package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessengerTest {
    @Test
    void echosMessage() {
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock("Sample user message");
        EchoProtocolMock echoProtocolMock = new EchoProtocolMock();
        Messenger messenger = new Messenger(echoProtocolMock);
        messenger.transport(clientConnectionMock);
        assertEquals("Sample user message", clientConnectionMock.toClient);
    }
}
