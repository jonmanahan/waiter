package waiter;

public record Router(Routes routes) {

    public Response getRequestedResponse(Request request) {
        if (!this.routes.exists(request)) {
            return new ResponseBuilder()
                    .newUp()
                    .status(Response.Status.NotFound.asString)
                    .build();
        }

        return this.routes.handle(request);
    }
}
