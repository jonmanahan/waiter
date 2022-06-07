package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import waiter.Connectable.ClientConnection;
import waiter.Interactive.mock.InteractorMock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientConnectionTest {

    @Property
    void readsMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        var byteInputStream = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        String message = new ClientConnection(new InteractorMock(byteInputStream)).read();

        assertEquals(message, userInput);
    }

    @Property
    void writesMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        var byteOutputStream = new ByteArrayOutputStream();
        new ClientConnection(new InteractorMock(byteOutputStream)).write(userInput);

        assertEquals(byteOutputStream.toString(), userInput + "\n");
    }

    @Test
    void closesSocket() throws IOException {
        InteractorMock interactorMock = new InteractorMock();

        new ClientConnection(interactorMock).close();

        assertTrue(interactorMock.connectionClosed);
    }
}
