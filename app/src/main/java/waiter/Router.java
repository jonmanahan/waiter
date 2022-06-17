package waiter;

import java.util.Map;

public class Router {

    private final Routes routes;

    public Router() {
        this.routes = new Routes().constructRoutes();
    }

    public Response getRequestedRoute(Request request) {
        Map<String, String> requestResult = this.routes.getRequestedResults(request.getUrl(), request.getMethod());

        return new Response(request.getProtocol(), requestResult.get("status"), requestResult.get("headers"), requestResult.get("body"));
    }
}
