package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    @Test
    void getSimpleGetReturnsOKRouteWithoutBody() {
        Request request = new Request("GET","/simple_get", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("200 OK", route.getStatus());
        assertEquals("Content-Length: 0", route.getHeaders());
        assertEquals("", route.getBody());
    }

    @Test
    void getSimpleGetWithBodyReturnsOKRouteWithBody() {
        Request request = new Request("GET","/simple_get_with_body", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("200 OK", route.getStatus());
        assertEquals("Content-Length: 11", route.getHeaders());
        assertEquals("Hello world", route.getBody());
    }

    @Test
    void headSimpleGetReturnsOKRouteWithoutBody() {
        Request request = new Request("HEAD","/simple_get", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("200 OK", route.getStatus());
        assertEquals("Content-Length: 0", route.getHeaders());
        assertEquals("", route.getBody());
    }

    @Test
    void NoMethodOrUrlFoundReturnsInternalServerErrorRouteWithoutBody() {
        Request request = new Request("foo","/bar", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("500 Internal Server Error", route.getStatus());
        assertEquals("Content-Length: 0", route.getHeaders());
        assertEquals("", route.getBody());
    }

    @Test
    void NoUrlFoundReturnsNotFoundRouteWithoutBody() {
        Request request = new Request("HEAD","/bar", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("404 Not Found", route.getStatus());
        assertEquals("Content-Length: 0", route.getHeaders());
        assertEquals("", route.getBody());
    }
}
