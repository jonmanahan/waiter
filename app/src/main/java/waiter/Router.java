package waiter;

import java.util.Map;

public class Router {

    Map<String, Map<String, String>> routes;

    public Router() {
        this.routes = Map.ofEntries(
                Map.entry("GET /simple_get", setResponseFields("200 OK", "Content-Length:", "")),
                Map.entry("GET /simple_get_with_body", setResponseFields("200 OK", "Content-Length:", "Hello world"))
        );
    }

    private Map<String, String> setResponseFields(String status, String headers, String body) {

        return Map.ofEntries(
                Map.entry("status", status),
                Map.entry("headers", headers.replace("Content-Length:", "Content-Length: " + body.length())),
                Map.entry("body", body)
        );
    }

    public Route getRoute(Request request) {

        String methodWithURI = request.getMethod() + " " + request.getUrl();
        Map<String, String> requestResult = routes.get(methodWithURI);
        return new Route(request.getProtocol(), requestResult.get("status"), requestResult.get("headers"), requestResult.get("body"));
    }
}
