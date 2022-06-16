package waiter.Protocol;

import waiter.*;

public record HttProtocol(RequestParser requestParser, Router router, ResponseFormatter responseFormatter) implements Protocol {

    public String serve(String fromClient) {

        Request request = requestParser.parse(fromClient);
        Route route = router.getRoute(request);

        return responseFormatter.formatResponse(route);
    }
}
