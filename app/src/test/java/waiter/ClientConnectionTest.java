package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import waiter.Connectable.ClientConnection;
import waiter.Socket.mock.ClientSocketMock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientConnectionTest {

    @Property
    void readsMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        String submittedUserInput = userInput + "\r\n";
        var byteInputStream = new ByteArrayInputStream(submittedUserInput.getBytes(StandardCharsets.UTF_8));
        String message = new ClientConnection(new ClientSocketMock(byteInputStream)).read();

        assertEquals(message, userInput);
    }

    @Property
    void writesMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        var byteOutputStream = new ByteArrayOutputStream();
        new ClientConnection(new ClientSocketMock(byteOutputStream)).write(userInput);

        assertEquals(byteOutputStream.toString(), userInput);
    }

    @Test
    void closesSocket() throws IOException {
        ClientSocketMock clientSocketMock = new ClientSocketMock();

        new ClientConnection(clientSocketMock).close();

        assertTrue(clientSocketMock.connectionClosed);
    }
}
