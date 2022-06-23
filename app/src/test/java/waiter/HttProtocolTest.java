package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.Size;
import waiter.Protocol.HttProtocol;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttProtocolTest {

    @Property
    void returnsOkWithNoBodyWhenUrlAndMethodExist(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        String request = String.join(" ", requestFields);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(requestFields.get(1), new String[]{requestFields.get(0)},
                        () -> new ResponseBuilder()
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
        assertTrue(response.endsWith("\r\n\r\n"));
    }

    @Property
    void returnsOkWithBodyWhenUrlAndMethodExistWithBody(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        String request = String.join(" ", requestFields);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(requestFields.get(1), new String[]{requestFields.get(0)},
                        () -> new ResponseBuilder()
                                        .newUp()
                                        .body(requestFields.get(2))
                                        .build()
                                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.endsWith(requestFields.get(2)));
    }

    @Property
    void returnsNotFoundWithReasonWhenNoMethodButExistingUrl(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        String request = String.join(" ", requestFields);
        Routes routes = new Routes();
        routes.addRoute(
                new Route(requestFields.get(1), new String[]{""},
                        () -> new ResponseBuilder()
                                        .newUp()
                                        .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
        assertTrue(response.endsWith("404, Found resource but no corresponding method"));
    }

    @Property
    void returnsNotFoundWithReasonWhenNoUrl(@ForAll @Size(3) List<@AlphaChars @NotBlank String> requestFields) {
        String request = String.join(" ", requestFields);
        Routes routes = new Routes();
        routes.addRoute(
                new Route("foo", new String[]{requestFields.get(0)},
                        () -> new ResponseBuilder()
                                        .newUp()
                                        .build()
                )
        );
        HttProtocol httProtocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );

        String response = httProtocol.serve(request);

        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
        assertTrue(response.endsWith("404, Could not find resource"));
    }
}
