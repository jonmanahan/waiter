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
        clientHasDisconnected = null;
    }

    @Test
    void echosSingleMessage() throws IOException {
        String[] clientInputs = {"foo", clientHasDisconnected};
        String[] expected = Arrays.copyOfRange(clientInputs, 0, clientInputs.length - 1);
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock(clientInputs);

        new Messenger(new EchoProtocolMock()).transport(clientConnectionMock);

        assertArrayEquals(expected, clientConnectionMock.echoedInputs);
    }

    @Test
    void echosManyMessages() throws IOException {
        String[] clientInputs = {"foo", "bar", "baz", clientHasDisconnected};
        String[] expected = Arrays.copyOfRange(clientInputs, 0, clientInputs.length - 1);
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock(clientInputs);

        new Messenger(new EchoProtocolMock()).transport(clientConnectionMock);

        assertArrayEquals(expected, clientConnectionMock.echoedInputs);
    }
}
