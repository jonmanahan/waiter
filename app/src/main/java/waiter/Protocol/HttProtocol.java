package waiter.Protocol;

import waiter.*;

public record HttProtocol(RequestParser requestParser, Router router, ResponseFormatter responseFormatter) implements Protocol {

    public String serve(String fromClient) {

        Request request = requestParser.parse(fromClient);
        Response Response = router.getRequestedRoute(request);

        return responseFormatter.formatResponse(Response);
    }
}
