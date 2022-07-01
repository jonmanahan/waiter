package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import waiter.Protocol.HttProtocol;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static waiter.RequestParser.END_OF_HEADERS;
import static waiter.RequestParser.END_OF_LINE;

public class HttProtocolTest {

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
