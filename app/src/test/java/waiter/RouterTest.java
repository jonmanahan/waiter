package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    @Test
    void getSimpleGetRoute() {
        Request request = new Request("GET","/simple_get", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("200 OK", route.getStatus());
        assertEquals("Content-Length: 0", route.getHeaders());
        assertEquals("", route.getBody());
    }

    @Test
    void getSimpleGetWithBodyRoute() {
        Request request = new Request("GET","/simple_get_with_body", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("200 OK", route.getStatus());
        assertEquals("Content-Length: 11", route.getHeaders());
        assertEquals("Hello world", route.getBody());
    }

    @Test
    void headSimpleGetRoute() {
        Request request = new Request("HEAD","/simple_get", "HTTP/1.1");

        Route route = new Router().getRoute(request);

        assertEquals("HTTP/1.1", route.getProtocol());
        assertEquals("200 OK", route.getStatus());
        assertEquals("Content-Length: 0", route.getHeaders());
        assertEquals("", route.getBody());
    }
}
