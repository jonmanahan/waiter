package waiter;

public class Response {

    public enum Status {
        OK("200 OK"),
        NotFound("404 Not Found")
        ;

        public final String asString;

        Status(final String responseStatus) {
            this.asString = responseStatus;
        }
    }

    private final String protocol;
    private final Response.Status status;
    private final String headers;
    private final String body;

    public Response(ResponseBuilder responseBuilder) {
        this.protocol = responseBuilder.protocol;
        this.status = responseBuilder.status;
        this.headers = responseBuilder.headers;
        this.body = responseBuilder.body;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public Response.Status getStatus() {
        return this.status;
    }

    public String getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }

    public String formatResponse() {
        return this.protocol + " " + this.status.asString + "\r\n"
                + this.headers + "\r\n\r\n" + this.body;
    }
}
