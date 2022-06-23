package waiter;

import java.util.Arrays;
import java.util.concurrent.Callable;

public record Route(String url, Request.Method[] methods, Callable<Response> handler) {

    public boolean methodExistsForUrl(String method) {
        return Arrays.toString(this.methods).contains(method);
    }
}
