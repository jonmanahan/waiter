package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServerProtocolTest {
    @Test
    void messageGivenBack() {
        Message fromClient = new Message("Sample user message");
        EchoServerProtocol echoServerProtocol = new EchoServerProtocol();
        Message response = echoServerProtocol.serve(fromClient);
        assertEquals(response, fromClient);
    }
}
