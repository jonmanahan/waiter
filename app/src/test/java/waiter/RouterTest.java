package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    @Property
    void returnsCorrespondingResponseForExistingRoute(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        String protocol = "PROTOCOL", status = "STATUS", headers = "HEADERS", body = "BODY";
        Request request = new Request(requestFields.get(0), requestFields.get(1), requestFields.get(2));
        Routes routes = new Routes();
        routes.addRoute(
                new Route(requestFields.get(0), new String[]{requestFields.get(1)},
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

    @Property
    void returnsNotFoundWithReasonResponseForNoExistingRoute(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        Request request = new Request(requestFields.get(0),requestFields.get(1), requestFields.get(2));

        Response Response = new Router(new Routes()).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("404 Not Found", Response.getStatus());
        assertEquals("Content-Length: " + Response.getBody().length(), Response.getHeaders());
        assertEquals("404, Could not find resource", Response.getBody());
    }

    @Property
    void returnsNotFoundWithReasonResponseForExistingRouteButNoMethod(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        Request request = new Request(requestFields.get(0),requestFields.get(1), requestFields.get(2));
        Routes routes = new Routes();
        routes.addRoute(
                new Route(requestFields.get(0), new String[]{""},
                        () -> new ResponseBuilder()
                                .newUp()
                                .build()
                )
        );
        Response Response = new Router(routes).getRequestedResponse(request);

        assertEquals("HTTP/1.1", Response.getProtocol());
        assertEquals("404 Not Found", Response.getStatus());
        assertEquals("Content-Length: " + Response.getBody().length(), Response.getHeaders());
        assertEquals("404, Found resource but no corresponding method", Response.getBody());
    }
}
