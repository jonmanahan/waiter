package waiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import waiter.Response.HeaderField;
import waiter.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    String url, protocol;

    @BeforeEach
    void setUp() {
        url = "URL";
        protocol = "PROTOCOL";
    }

    @Test
    void returnsCorrespondingResponseForExistingRoute() {
        String headers = "HEADERS", body = "BODY";
        Status status = Status.OK;
        Request request = new Request(url, Request.Method.GET.asString, protocol);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        () -> new ResponseBuilder()
                                .newUp()
                                .protocol(protocol)
                                .status(status)
                                .headers(HeaderField.Allow, headers)
                                .body(body)
                                .build()
                )
        );

        Response response = new Router(routes).getRequestedResponse(request);

        assertEquals(protocol, response.getProtocol());
        assertEquals(status, response.getStatus());
        assertEquals(headers, response.getHeaders().get(HeaderField.Allow));
        assertEquals(body, response.getBody());
    }

    @Test
    void returnsNotFoundWithReasonResponseForNoExistingRoute() {
        Request request = new Request(url, Request.Method.GET.asString, protocol);

        Response Response = new Router(new Routes()).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(Status.NotFound, Response.getStatus());
        String contentLengthHeader = HeaderField.ContentLength.asString + Response.getHeaders().get(HeaderField.ContentLength);
        assertEquals("Content-Length: 28", contentLengthHeader);
        assertEquals("404, Could not find resource", Response.getBody());
    }

    @Test
    void returnsNotFoundWithNoReasonResponseForExistingUrlButButNoHeadMethodInRoute() {
        Request request = new Request(url, Request.Method.HEAD.asString, protocol);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{},
                        () -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        Response Response = new Router(routes).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(Status.MethodNotAllowed, Response.getStatus());
        String contentLengthHeader = HeaderField.ContentLength.asString + Response.getHeaders().get(HeaderField.ContentLength);
        assertEquals("Content-Length: 0", contentLengthHeader);
        assertEquals("", Response.getBody());
    }

    @Test
    void returnsMethodNotAllowedWithReasonResponseForExistingUrlButNoGetMethodInRoute() {
        Request request = new Request(url, Request.Method.GET.asString, protocol);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{},
                        () -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        Response Response = new Router(routes).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(Status.MethodNotAllowed, Response.getStatus());
        String contentLengthHeader = HeaderField.ContentLength.asString + Response.getHeaders().get(HeaderField.ContentLength);
        assertEquals("Content-Length: 0", contentLengthHeader);
        assertEquals("", Response.getBody());
    }
}
