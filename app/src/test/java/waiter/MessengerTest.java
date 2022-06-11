package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import waiter.Connectable.mock.ClientConnectionMock;
import waiter.Protocol.mock.EchoProtocolMock;
import waiter.Transportable.Messenger;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MessengerTest {

    String clientHasDisconnected;

    @BeforeEach
    void setUp() {
        // Needs to be null because reading the input stream returns null when the sockets are disconnected
        // which signals the client is disconnected
        clientHasDisconnected = null;
    }

    @Property
    void echosSingleMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        String[] clientInputs = {userInput, clientHasDisconnected};
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock(clientInputs);

        new Messenger(new EchoProtocolMock()).transport(clientConnectionMock);

        assertArrayEquals(new String[]{userInput}, clientConnectionMock.echoedInputs);
    }

    @Disabled
    @Property
    void echosManyMessages(@ForAll @Size(3) List<@AlphaChars @NotBlank String> userInputs) throws IOException {
        String[] expectedInputs = userInputs.toArray(new String[3]);
        userInputs.add(clientHasDisconnected);
        String[] clientInputs = userInputs.toArray(new String[4]);
        ClientConnectionMock clientConnectionMock = new ClientConnectionMock(clientInputs);

        new Messenger(new EchoProtocolMock()).transport(clientConnectionMock);

        assertArrayEquals(expectedInputs, clientConnectionMock.echoedInputs);
    }
}
