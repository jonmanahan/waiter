package waiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Routes {

    public Map<String, Route> routes;

    public Routes() {
        this.routes = new HashMap<>();
    }

    public void addRoute(Route route) {
        this.routes.put(route.getUrl(), route);
    }

    public boolean exists(Request request) {
        return this.routes.containsKey(request.getUrl());
    }

    public Response handle(Request request) {
        Route route = this.routes.get(request.getUrl());
        if(!route.methodExistsForUrl(request.getMethod())) {
            return new ResponseBuilder()
                    .newUp()
                    .status("404 Not Found")
                    .build();
        }

        Callable<Response> handler = route.getHandler();
        try {
            return handler.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
