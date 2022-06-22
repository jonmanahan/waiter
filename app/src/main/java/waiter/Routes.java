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

    public Route getRoute(String url) {
        return this.routes.get(url);
    }

    public boolean exists(Request request) {
        return this.routes.containsKey(request.getUrl());
    }

    public Response handle(Route route) {

        Callable<Response> handler = route.getHandler();
        try {
            return handler.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
