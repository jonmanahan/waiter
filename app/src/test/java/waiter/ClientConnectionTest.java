package waiter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import waiter.ClientConnection.ClientConnection;

import java.io.IOException;

public class ClientConnectionTest {

    @Test
    void readsMessage() throws IOException {
        String input = "foo";
        InputStreamerMock inputStreamerMock = new InputStreamerMock(input);
        OutputStreamerMock outputStreamerMock = new OutputStreamerMock();
        ClientConnection clientConnection = new ClientConnection(inputStreamerMock, outputStreamerMock);
        Message readMessage = clientConnection.read();
        assertEquals(readMessage.open(), input);
    }

    @Test
    void writesMessage() throws IOException {
        String input = "foo";
        Message outputMessage = new Message(input);
        InputStreamerMock inputStreamerMock = new InputStreamerMock(input);
        OutputStreamerMock outputStreamerMock = new OutputStreamerMock();
        ClientConnection clientConnection = new ClientConnection(inputStreamerMock, outputStreamerMock);
        clientConnection.write(outputMessage);
        assertEquals(outputStreamerMock.writtenOutput, outputMessage.open());
    }
}
