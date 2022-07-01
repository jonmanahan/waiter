package waiter;

import java.util.Arrays;
import java.util.function.Function;

public record Route(String url, Request.Method[] methods, Function<Request, Response> handler) {

    public boolean methodExistsForUrl(String method) {
        return Arrays.toString(this.methods).contains(method);
    }
}
