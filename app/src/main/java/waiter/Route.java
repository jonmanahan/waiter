package waiter;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Route {


    private final String url;
    private final String[] methods;
    private final Callable<Response> handler;

    public Route(RouteBuilder routeBuilder) {
        //Just have the builder put in strings and the handler, then populate each route in routes
        //Maybe have a have the route for the key, being the url and the method together, then have the value be the route object
        this.url = routeBuilder.url;
        this.methods = routeBuilder.methods;
        this.handler = routeBuilder.handler;
    }

    public String getUrl() {
        return this.url;
    }

    public String[] getMethods() {
        return this.methods;
    }

    public Callable<Response> getHandler() {
        return this.handler;
    }

    public boolean methodExistsForUrl(String method) {
        return Arrays.toString(this.methods).contains(method);
    }
}
