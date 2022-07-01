package waiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequesterParserTest {

    String method, url, protocol;

    @BeforeEach
    void setUp() {
        method = "METHOD";
        url = "URL";
        protocol = "PROTOCOL";
    }

    @Test
    void parsedRequestNoBodyCreated() {
        RequestParser requestParser = new RequestParser();
        String headers = "Content-Length: 0";
        String body = "";
        String expectedRequest = String.format(
                """
                %s %s %s
                %s
                
                %s""", method, url, protocol, headers, body).replace("\n", "\r\n");

        Request request = requestParser.parse(expectedRequest);

        assertEquals(request.getMethod(), method);
        assertEquals(request.getUrl(), url);
        assertEquals(request.getProtocol(), protocol);
        assertEquals(request.getHeaders(), headers);
        assertEquals(request.getBody(), body);
    }

    @Test
    void parsedRequestWithBodyCreated() {
        RequestParser requestParser = new RequestParser();
        String body = "I WANNA POST SOMETHING";
        String headers = "Content-Length: " + body.length();
        String expectedRequest = String.format(
                """
                %s %s %s
                %s
                
                %s""", method, url, protocol, headers, body).replace("\n", "\r\n");

        Request request = requestParser.parse(expectedRequest);

        assertEquals(request.getMethod(), method);
        assertEquals(request.getUrl(), url);
        assertEquals(request.getProtocol(), protocol);
        assertEquals(request.getHeaders(), headers);
        assertEquals(request.getBody(), body);
    }
}
