package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    @Test
    void getSimpleGetReturnsOKRouteWithoutBody() {
        Request request = new Request("GET","/simple_get", "HTTP/1.1");

        Response Response = new Router().getRequestedRoute(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("200 OK", Response.getStatus());
        assertEquals("Content-Length: 0", Response.getHeaders());
        assertEquals("", Response.getBody());
    }

    @Test
    void getSimpleGetWithBodyReturnsOKRouteWithBody() {
        Request request = new Request("GET","/simple_get_with_body", "HTTP/1.1");

        Response Response = new Router().getRequestedRoute(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("200 OK", Response.getStatus());
        assertEquals("Content-Length: 11", Response.getHeaders());
        assertEquals("Hello world", Response.getBody());
    }

    @Test
    void headSimpleGetReturnsOKRouteWithoutBody() {
        Request request = new Request("HEAD","/simple_get", "HTTP/1.1");

        Response Response = new Router().getRequestedRoute(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("200 OK", Response.getStatus());
        assertEquals("Content-Length: 0", Response.getHeaders());
        assertEquals("", Response.getBody());
    }

    @Test
    void NoMethodOrUrlFoundReturnsInternalServerErrorRouteWithoutBody() {
        Request request = new Request("foo","/bar", "HTTP/1.1");

        Response Response = new Router().getRequestedRoute(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("404 Not Found", Response.getStatus());
        assertEquals("Content-Length: 0", Response.getHeaders());
        assertEquals("", Response.getBody());
    }

    @Test
    void NoUrlFoundReturnsNotFoundRouteWithoutBody() {
        Request request = new Request("HEAD","/bar", "HTTP/1.1");

        Response Response = new Router().getRequestedRoute(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("404 Not Found", Response.getStatus());
        assertEquals("Content-Length: 0", Response.getHeaders());
        assertEquals("", Response.getBody());
    }
}
