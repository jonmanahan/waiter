package waiter;

import java.util.concurrent.Callable;

public class RouteBuilder {

    public String url;
    public String[] methods;
    public Callable<Response> handler;

    public RouteBuilder newUp() {
        return new RouteBuilder();
    }

    public RouteBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RouteBuilder methods(String[] methods) {
        this.methods = methods;
        return this;
    }

    public RouteBuilder handler(Callable<Response> handler) {
        this.handler = handler;
        return this;
    }

    public Route build() {
        return new Route(this);
    }
}
