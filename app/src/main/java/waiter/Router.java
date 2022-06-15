package waiter;

import java.util.Map;

public class Router {

    private final Map<String, Map<String, Map<String, String>>> routes;
    private final String noMethod = "No Method";
    private final String noUrl = "No Url";

    public Router() {
        this.routes = Map.ofEntries(
                Map.entry("GET", Map.ofEntries(
                        Map.entry("/simple_get", setResponseFields("200 OK", "Content-Length:", "")),
                        Map.entry("/simple_get_with_body", setResponseFields("200 OK", "Content-Length:", "Hello world")),
                        //This will become a different response when Method Not Allowed is implemented
                        Map.entry(noUrl, setResponseFields("500 Internal Server Error", "Content-Length:", ""))
                )),
                Map.entry(noMethod, Map.ofEntries(
                        Map.entry(noUrl, setResponseFields("500 Internal Server Error", "Content-Length:", ""))
                ))
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

        Map<String, Map<String, String>> methodAssociatedUrls = routes.getOrDefault(request.getMethod(), routes.get(noMethod));
        Map<String, String> requestResult = methodAssociatedUrls.getOrDefault(request.getUrl(), methodAssociatedUrls.get(noUrl));

        return new Route(request.getProtocol(), requestResult.get("status"), requestResult.get("headers"), requestResult.get("body"));
    }
}
