package waiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import waiter.Response.HeaderField;
import waiter.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    String url, protocol, requestHeaders, requestBody;

    @BeforeEach
    void setUp() {
        url = "URL";
        protocol = "PROTOCOL";
        requestHeaders = "REQUEST";
        requestBody = "REQUEST";
    }

    @Test
    void returnsCorrespondingResponseForExistingRoute() {
        String responseHeaders = "RESPONSE", responseBody = "RESPONSE";
        Status status = Status.OK;
        Request request = new Request(url, Request.Method.GET.asString, protocol, requestHeaders, requestBody);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        requestMessage -> new ResponseBuilder()
                                .newUp()
                                .protocol(protocol)
                                .status(status)
                                .headers(HeaderField.Allow, responseHeaders)
                                .body(responseBody)
                                .build()
                )
        );

        Response response = new Router(routes).getRequestedResponse(request);

        assertEquals(protocol, response.getProtocol());
        assertEquals(status, response.getStatus());
        assertEquals(responseHeaders, response.getHeaders().get(HeaderField.Allow));
        assertEquals(responseBody, response.getBody());
    }

    @Test
    void returnsNotFoundWithReasonResponseForNoExistingRoute() {
        Request request = new Request(url, Request.Method.GET.asString, protocol, requestHeaders, requestBody);

        Response Response = new Router(new Routes()).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(Status.NotFound, Response.getStatus());
        String contentLengthHeader = HeaderField.ContentLength.asString + Response.getHeaders().get(HeaderField.ContentLength);
        assertEquals("Content-Length: 28", contentLengthHeader);
        assertEquals("404, Could not find resource", Response.getBody());
    }

    @Test
    void returnsNotFoundWithNoReasonResponseForExistingUrlButButNoHeadMethodInRoute() {
        Request request = new Request(url, Request.Method.HEAD.asString, protocol, requestHeaders, requestBody);

        Response Response = new Router(new Routes()).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(Status.NotFound, Response.getStatus());
        String contentLengthHeader = HeaderField.ContentLength.asString + Response.getHeaders().get(HeaderField.ContentLength);
        assertEquals("Content-Length: 0", contentLengthHeader);
        assertEquals("", Response.getBody());
    }

    @Test
    void returnsMethodNotAllowedWithReasonResponseForExistingUrlButNoGetMethodInRoute() {
        Request request = new Request(url, Request.Method.GET.asString, protocol, requestHeaders, requestBody);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{},
                        requestMessage -> new ResponseBuilder()
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
    void returnsMovedPermanentlyWithLocationHeaderForResourceThatHasBeenAssignedNewUrl() {
        Request request = new Request(url, Request.Method.GET.asString, protocol, requestHeaders, requestBody);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(url, new Request.Method[]{Request.Method.GET},
                        requestMessage -> new ResponseBuilder()
                                .newUp()
                                .status(Status.MovedPermanently)
                                .headers(HeaderField.Location, "http://127.0.0.1:5000/simple_get")
                                .build()
                )
        );
        Response Response = new Router(routes).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals(Status.MovedPermanently, Response.getStatus());
        String contentLengthHeader = HeaderField.ContentLength.asString + Response.getHeaders().get(HeaderField.ContentLength);
        assertEquals("Content-Length: 0", contentLengthHeader);
        String locationHeader = HeaderField.Location.asString + Response.getHeaders().get(HeaderField.Location);
        assertEquals("Location: http://127.0.0.1:5000/simple_get", locationHeader);
        assertEquals("", Response.getBody());
    }
}
