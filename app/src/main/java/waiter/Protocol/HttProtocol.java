package waiter.Protocol;

import waiter.*;

public record HttProtocol(RequestParser requestParser, Router router, ResponseBuilder responseBuilder) implements Protocol {

    public String serve(String fromClient) {

        Request request = requestParser.parse(fromClient);
        Route route = router.getRoute(request);

        return responseBuilder.buildResponse(route);
    }
}
