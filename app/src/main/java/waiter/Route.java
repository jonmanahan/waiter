package waiter;

import java.util.Arrays;
import java.util.function.Function;

public class Route {

    public final String url;
    public final Request.Method[] methods;
    public final Function<Request, Response> handler;

    public Route(String url, Request.Method[] methods, Function<Request, Response> handler) {
        this.url = url;
        this.methods = addOptionsMethodToMethods(methods);
        this.handler = handler;
    }

    public boolean methodExistsForUrl(String method) {
        return Arrays.toString(this.methods).contains(method);
    }

    private Request.Method[] addOptionsMethodToMethods(Request.Method[] methods) {
        Request.Method[] methodsWithOptions = Arrays.copyOf(methods, methods.length + 1);
        methodsWithOptions[methods.length] = Request.Method.OPTIONS;
        return methodsWithOptions;
    }
}
