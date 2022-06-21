package waiter.Protocol;

import waiter.*;

public record HttProtocol(RequestParser requestParser, Router router) implements Protocol {

    public String serve(String fromClient) {

        Request request = requestParser.parse(fromClient);
        Response response = router.getRequestedResponse(request);

        return response.formatResponse();
    }
}
