package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import waiter.Protocol.HttProtocol;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static waiter.RequestParser.END_OF_HEADERS;
import static waiter.RequestParser.END_OF_LINE;

public class HttProtocolTest {

    @Test
    void returnsOkWithNoBodyWhenGetMethodWithExistingUrl() {
        String request = "GET /simple_get HTTP/1.1" + END_OF_LINE + "foo" + END_OF_HEADERS;
        Routes routes = new Routes();
        routes.addRoute(
                new Route("/simple_get", new Request.Method[]{Request.Method.GET},
                        requestMessage -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith(END_OF_HEADERS));
    }

    @Test
    void returnsOkWithNoBodyWhenHeadMethodWithExistingUrl() {
        String request = "HEAD /simple_get HTTP/1.1" + END_OF_LINE + "foo" + END_OF_HEADERS;
        Routes routes = new Routes();
        routes.addRoute(
                new Route("/simple_get", new Request.Method[]{Request.Method.HEAD},
                        requestMessage -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith(END_OF_HEADERS));
    }

    @Test
    void returnsOkWithBodyWhenPostMethodWithExistingUrl() {
        String body = "body";
        String request = "POST /echo_body HTTP/1.1"  + END_OF_LINE + "foo" + END_OF_HEADERS + body;
        Routes routes = new Routes();
        routes.addRoute(
                new Route("/echo_body", new Request.Method[]{Request.Method.POST},
                        requestMessage -> new ResponseBuilder()
                                .newUp()
                                .body(requestMessage.getBody())
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith(body));
    }

    @Property
    void returnsOkWithNoBodyWhenExistingMethodWithExistingUrl(@ForAll @AlphaChars @NotBlank String url) {
        String request = "GET " + url + " HTTP/1.1" + END_OF_LINE + "foo" + END_OF_HEADERS;
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        requestMessage -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith(END_OF_HEADERS));
    }
}
