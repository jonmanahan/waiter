package waiter;

import java.util.Map;

public class Router {

    Map<String, Map<String, String>> routes;

    public Router() {
        this.routes = Map.ofEntries(
                Map.entry("GET /simple_get", Map.ofEntries(
                        Map.entry("status", "200 OK"),
                        Map.entry("headers", "Accept-Language: en-US\r\nContent-Length: 0"),
                        Map.entry("body", "")
                )),
                Map.entry("GET /simple_get_with_body", Map.ofEntries(
                        Map.entry("status", "200 OK"),
                        Map.entry("headers", "Accept-Language: en-US\r\nContent-Length: 11"),
                        Map.entry("body", "Hello world")
                ))
        );
    }

    public Route getRoute(Request request) {

        String methodWithURI = request.getMethod() + " " + request.getUrl();
        Map<String, String> requestResult = routes.get(methodWithURI);
        return new Route(request.getProtocol(), requestResult.get("status"), requestResult.get("headers"), requestResult.get("body"));
    }
}
