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
        this.routes.put(route.url(), route);
    }

    public Route getRoute(String url) {
        return this.routes.get(url);
    }

    public boolean exists(String url) {
        return this.routes.containsKey(url);
    }

    public Response handle(Route route) {

        Callable<Response> handler = route.handler();
        try {
            return handler.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
