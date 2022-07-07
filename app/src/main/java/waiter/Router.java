package waiter;

import java.util.ArrayList;
import java.util.Objects;

public record Router(Routes routes) {

    public Response getRequestedResponse(Request request) {
        String url = request.getUrl(), method = request.getMethod();
        if (!this.routes.exists(url)) {
            return get404Response(method);
        }

        Route route = this.routes.getRoute(url);

        if(!route.methodExistsForUrl(method)) {
            return get405Response(route);
        }

        if(request.methodIsOptions()) {
            return getOptionsResponse(route);
        }

        return this.routes.handle(request, route);
    }

    private Response getOptionsResponse(Route route) {

        return new ResponseBuilder()
                .newUp()
                .status(Response.Status.OK)
                .headers(Response.HeaderField.Allow, formatMethods(route.methods))
                .build();
    }

    private Response get405Response(Route route) {

        return new ResponseBuilder()
                .newUp()
                .status(Response.Status.MethodNotAllowed)
                .headers(Response.HeaderField.Allow, formatMethods(route.methods))
                .build();
    }

    private String formatMethods(Request.Method[] methods) {
        ArrayList<String> formattedMethods = new ArrayList<>();
        for(Request.Method method : methods) {
            formattedMethods.add(method.asString);
        }
        return String.join(", ", formattedMethods);
    }

    private Response get404Response(String method) {
        String reason = "404, Could not find resource";
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
