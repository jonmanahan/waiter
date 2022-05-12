package waiter;

import waiter.ClientConnection.ClientConnection;
import waiter.InputStreamer.mock.InputStreamerMock;
import waiter.OutputStreamer.mock.OutputStreamerMock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class ClientConnectionTest {

    @Test
    void readsMessage() throws IOException {
        String input = "foo";
        String message = new ClientConnection(new InputStreamerMock(input), new OutputStreamerMock()).read().open();
        assertEquals(message, input);
    }

    @Test
    void writesMessage() throws IOException {
        OutputStreamerMock outputStreamerMock = new OutputStreamerMock();
        ClientConnection clientConnection = new ClientConnection(new InputStreamerMock("foo"), outputStreamerMock);
        clientConnection.write(new Message("foo"));
        assertEquals(outputStreamerMock.writtenOutput, "foo");
    }
}
