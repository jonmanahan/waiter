package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import waiter.ClientConnection.ClientConnection;
import waiter.InputStreamer.mock.InputStreamerMock;
import waiter.Interactor.mock.InteractorMock;
import waiter.OutputStreamer.mock.OutputStreamerMock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientConnectionTest {

    @Property
    void readsMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        String message = new ClientConnection(
                new InteractorMock(),
                new InputStreamerMock(userInput),
                new OutputStreamerMock()
        ).read();

        assertEquals(message, userInput);
    }

    @Property
    void writesMessage(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        OutputStreamerMock outputStreamerMock = new OutputStreamerMock();

        new ClientConnection(
                new InteractorMock(),
                new InputStreamerMock("foo"),
                outputStreamerMock
        ).write(userInput);

        assertEquals(outputStreamerMock.writtenOutput, userInput);
    }

    @Test
    void closesSocket() throws IOException {
        InteractorMock interactorMock = new InteractorMock();

        new ClientConnection(
                interactorMock,
                new InputStreamerMock("foo"),
                new OutputStreamerMock()
        ).close();

        assertTrue(interactorMock.connectionClosed);
    }
}
