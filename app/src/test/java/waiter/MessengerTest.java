package waiter;

import waiter.ClientConnection.mock.ClientConnectionMock;
import waiter.EchoProtocol.mock.EchoProtocolMock;
import waiter.Messenger.Messenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class MessengerTest {
    @Test
    void echosMessage() throws IOException {
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock("Sample user message");
        EchoProtocolMock echoProtocolMock = new EchoProtocolMock();
        Messenger messenger = new Messenger(echoProtocolMock);
        messenger.transport(clientConnectionMock);
        assertEquals("Sample user message", clientConnectionMock.toClient);
    }
}
