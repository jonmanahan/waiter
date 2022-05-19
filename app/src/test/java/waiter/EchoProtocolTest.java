package waiter;

import waiter.EchoProtocol.EchoProtocol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoProtocolTest {

    @Test
    void messageGivenBack() {
        String fromClient = "Sample user message";
        EchoProtocol echoProtocol = new EchoProtocol();
        String response = echoProtocol.serve(fromClient);
        assertEquals(response, fromClient);
    }
}
