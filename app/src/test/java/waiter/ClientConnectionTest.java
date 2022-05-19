package waiter;

import waiter.ClientConnection.ClientConnection;
import waiter.InputStreamer.mock.InputStreamerMock;
import waiter.Interactor.mock.InteractorMock;
import waiter.OutputStreamer.mock.OutputStreamerMock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

class ClientConnectionTest {

    @Test
    void readsMessage() throws IOException {
        String input = "foo";

        String message = new ClientConnection(
                new InteractorMock(),
                new InputStreamerMock(input),
                new OutputStreamerMock()
        ).read();

        assertEquals(message, input);
    }

    @Test
    void writesMessage() throws IOException {
        OutputStreamerMock outputStreamerMock = new OutputStreamerMock();

        new ClientConnection(
                new InteractorMock(),
                new InputStreamerMock("foo"),
                outputStreamerMock
        ).write("foo");

        assertEquals(outputStreamerMock.writtenOutput, "foo");
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
