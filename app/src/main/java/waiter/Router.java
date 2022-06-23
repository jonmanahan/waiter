package waiter;

public record Router(Routes routes) {

    public Response getRequestedResponse(Request request) {
        if (!this.routes.exists(request)) {
            return new ResponseBuilder()
                    .newUp()
                    .status(Response.Status.NotFound)
                    .body("404, Could not find resource")
                    .build();
        }

        Route route = this.routes.getRoute(request.getUrl());

        if(!route.methodExistsForUrl(request.getMethod())) {
            return new ResponseBuilder()
                    .newUp()
                    .status(Response.Status.NotFound)
                    .body("404, Found resource but no corresponding method")
                    .build();
        }

        return this.routes.handle(route);
    }
}
