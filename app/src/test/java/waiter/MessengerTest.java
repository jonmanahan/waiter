package waiter;

import waiter.ClientConnection.mock.ClientConnectionMock;
import waiter.EchoProtocol.mock.EchoProtocolMock;
import waiter.Messenger.Messenger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.util.Arrays;

class MessengerTest {

    String clientHasDisconnected;

    @BeforeEach
    void setUp() {
        // Needs to be null because reading the input stream returns null when the sockets are disconnected
        // which signals the client is disconnected
        clientHasDisconnected = null;
    }

    @Test
    void echosSingleMessage() throws IOException {
        String[] clientInputs = {"foo", clientHasDisconnected};
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock(clientInputs);

        new Messenger(new EchoProtocolMock()).transport(clientConnectionMock);

        assertArrayEquals(new String[]{"foo"}, clientConnectionMock.echoedInputs);
    }

    @Test
    void echosManyMessages() throws IOException {
        String[] clientInputs = {"foo", "bar", "baz", clientHasDisconnected};
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock(clientInputs);

        new Messenger(new EchoProtocolMock()).transport(clientConnectionMock);

        assertArrayEquals(new String[]{"foo", "bar", "baz"}, clientConnectionMock.echoedInputs);
    }
}
