package waiter;

public record ResponseFormatter() {

    public String formatResponse(Route route) {
        return route.getProtocol() + " " + route.getStatus() + "\r\n"
                + route.getHeaders() + "\r\n\r\n" + route.getBody();
    }
}
