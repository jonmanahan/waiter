package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttProtocolTest {

    @Test
    void simpleGetResponse() {
        String fromClient = "GET /simple_get HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol();

        String response = httProtocol.serve(fromClient);

        assertEquals(response, "HTTP/1.1 200 OK");
    }

    @Test
    void simpleGetWithBodyResponse() {
        String fromClient = "GET /simple_get_with_body HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol();

        String response = httProtocol.serve(fromClient);

        assertEquals(response, "HTTP/1.1 200 OK\n\n Hello world");
    }
}
