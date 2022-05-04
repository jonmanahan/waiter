package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessengerTest {
    @Test
    void echosMessage() {
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock("Sample user message");
        EchoProtocol echoProtocol = new EchoProtocol();
        Messenger messenger = new Messenger(echoProtocol);
        messenger.transport(clientConnectionMock);
        assertEquals(clientConnectionMock.toClient, "Sample user message");
    }
}
