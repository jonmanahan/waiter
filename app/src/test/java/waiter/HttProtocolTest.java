package waiter;

import org.junit.jupiter.api.Test;
import waiter.Protocol.HttProtocol;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttProtocolTest {

    @Test
    void getSimpleGetResponse() {
        String fromClient = "GET /simple_get HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(new RequestParser(), new Router(), new ResponseBuilder());

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
    }

    @Test
    void getSimpleGetWithBodyResponse() {
        String fromClient = "GET /simple_get_with_body HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(new RequestParser(), new Router(), new ResponseBuilder());

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("\r\n\r\nHello world"));
    }

    @Test
    void headSimpleGetResponse() {
        String fromClient = "HEAD /simple_get HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(new RequestParser(), new Router(), new ResponseBuilder());

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
    }
}
