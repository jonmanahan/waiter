package waiter;

import org.junit.jupiter.api.Test;
import waiter.Protocol.HttProtocol;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttProtocolTest {

    @Test
    void simpleGetResponse() {
        String fromClient = "GET /simple_get HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol();

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
    }

    @Test
    void simpleGetWithBodyResponse() {
        String fromClient = "GET /simple_get_with_body HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol();

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("\n\nHello world"));
    }
}
