package waiter;

public record ResponseFormatter() {

    public String formatResponse(Response Response) {
        return Response.getProtocol() + " " + Response.getStatus() + "\r\n"
                + Response.getHeaders() + "\r\n\r\n" + Response.getBody();
    }
}
