package waiter;

import org.junit.jupiter.api.Test;
import waiter.Protocol.HttProtocol;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttProtocolTest {

    @Test
    void getSimpleGetReturnsOkWithoutBody() {
        String fromClient = "GET /simple_get HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(new RequestParser(), new Router(), new ResponseFormatter());

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Test
    void getSimpleGetWithBodyReturnsOKWithBody() {
        String fromClient = "GET /simple_get_with_body HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(),
                new ResponseFormatter()
        );

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\nHello world"));
    }

    @Test
    void headSimpleGetReturnsOKWithoutBody() {
        String fromClient = "HEAD /simple_get HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(),
                new ResponseFormatter()
        );

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Test
    void NoMethodNoUrlReturnsInternalServerErrorWithoutBody() {
        String fromClient = "foo /bar HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(),
                new ResponseFormatter()
        );

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Test
    void NoUrlReturnsNotFoundWithoutBody() {
        String fromClient = "HEAD /bar HTTP/1.1";
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(),
                new ResponseFormatter()
        );

        String response = httProtocol.serve(fromClient);

        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }
}
