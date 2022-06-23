package waiter;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Route {


    private final String url;
    private final String[] methods;
    private final Callable<Response> handler;

    public Route(String url, String[] methods, Callable<Response> handler) {
        this.url = url;
        this.methods = methods;
        this.handler = handler;
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
