package waiter;

import java.util.Objects;

public record Router(Routes routes) {

    public Response getRequestedResponse(Request request) {
        String url = request.getUrl(), method = request.getMethod();
        if (!this.routes.exists(url)) {
            return get404ResponseWithReason(method, "404, Could not find resource");
        }

        Route route = this.routes.getRoute(url);

        if(!route.methodExistsForUrl(method)) {
            return get404ResponseWithReason(method, "404, Found resource but no corresponding method");
        }

        return this.routes.handle(route);
    }

    private Response get404ResponseWithReason (String method, String reason) {
        if(Objects.equals(method, Request.Method.HEAD.asString)) {
            reason = "";
        }

        return new ResponseBuilder()
                .newUp()
                .status(Response.Status.NotFound)
                .body(reason)
                .build();
    }
}
