package waiter;

public record ResponseBuilder() {

    public String buildResponse(Route route) {
        return route.getProtocol() + " " + route.getStatus() + "\r\n"
                + route.getHeaders() + "\r\n\r\n" + route.getBody();
    }
}
