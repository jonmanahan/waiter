package waiter;

public class Response {

    private final String protocol;
    private final String status;
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

    public String getStatus() {
        return this.status;
    }

    public String getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }

    public String formatResponse() {
        return this.protocol + " " + this.status + "\r\n"
                + this.headers + "\r\n\r\n" + this.body;
    }
}
