package waiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    String url, protocol, headers, body;
    Response.Status status;


    @BeforeEach
    void setUp() {
        url = "URL"; protocol = "PROTOCOL"; headers = "HEADERS"; body = "BODY";
        status = Response.Status.OK;
    }

    @Test
    void returnsCorrespondingResponseForExistingRoute() {

        Request request = new Request(url, Request.Method.GET.asString, protocol);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        () -> new ResponseBuilder()
                                .newUp()
                                .protocol(protocol)
                                .status(status)
                                .headers(headers)
                                .body(body)
                                .build()
                )
        );

        Response response = new Router(routes).getRequestedResponse(request);

        assertEquals(protocol, response.getProtocol());
        assertEquals(status, response.getStatus());
        assertEquals(headers, response.getHeaders());
        assertEquals(body, response.getBody());
    }

    @Test
    void returnsNotFoundWithReasonResponseForNoExistingRoute() {
        Request request = new Request(url, Request.Method.GET.asString, protocol);

        Response Response = new Router(new Routes()).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(waiter.Response.Status.NotFound, Response.getStatus());
        assertEquals("Content-Length: " + Response.getBody().length(), Response.getHeaders());
        assertEquals("404, Could not find resource", Response.getBody());
    }

    @Test
    void returnsNotFoundWithReasonResponseForExistingUrlButNoGetMethodInRoute() {
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
        assertEquals(waiter.Response.Status.NotFound, Response.getStatus());
        assertEquals("Content-Length: " + Response.getBody().length(), Response.getHeaders());
        assertEquals("404, Found resource but no corresponding method", Response.getBody());
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
        assertEquals(waiter.Response.Status.NotFound, Response.getStatus());
        assertEquals("Content-Length: " + Response.getBody().length(), Response.getHeaders());
        assertEquals("", Response.getBody());
    }
}
