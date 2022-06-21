package waiter;

public class Router {

    private final Routes routes;

    public Router(Routes routes) {
        this.routes = routes;
    }

    public Response getRequestedResponse(Request request) {
        if(!this.routes.exists(request)) {
            return new ResponseBuilder()
                    .newUp()
                    .status("404 Not Found")
                    .build();
        }

        return this.routes.handle(request);
    }
}
