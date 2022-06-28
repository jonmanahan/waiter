package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import waiter.Protocol.HttProtocol;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttProtocolTest {

    @Test
    void returnsOkWithNoBodyWhenGetMethodWithExistingUrl() {
        String url = "/simple_get";
        String requestStartLine = String.format("GET %s HTTP/1.1", url);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        request -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(requestStartLine);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Test
    void returnsOkWithNoBodyWhenHeadMethodWithExistingUrl() {
        String url = "/simple_get";
        String requestStartLine = String.format("HEAD %s HTTP/1.1", url);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.HEAD},
                        request -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(requestStartLine);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Test
    void returnsOkWithBodyWhenPostMethodWithExistingUrl() {
        String url = "/echo_body";
        String requestStartLine = String.format("POST %s HTTP/1.1", url);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.POST},
                        request -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(requestStartLine);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Property
    void returnsOkWithNoBodyWhenExistingMethodWithExistingUrl(@ForAll @AlphaChars @NotBlank String url) {
        String requestStartLine = String.format("GET %s HTTP/1.1", url);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        request -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(requestStartLine);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith("\r\n\r\n"));
    }
}
