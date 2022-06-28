package waiter;

import java.util.Map;
import java.util.TreeMap;

public class Response {

    public enum Status {
        OK("200 OK"),
        NotFound("404 Not Found"),
        MethodNotAllowed("405 Method Not Allowed")
        ;

        public final String asString;

        Status(final String responseStatus) {
            this.asString = responseStatus;
        }
    }

    public enum HeaderField {
        Allow("Allow: "),
        ContentLength("Content-Length: "),
        ContentType("Content-Type: ")
        ;

        public final String asString;

        HeaderField(final String header) {
            this.asString = header;
        }
    }

    private final String protocol;
    private final Response.Status status;
    private final TreeMap<HeaderField, String> headers;
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

    public TreeMap<HeaderField, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }

    public String formatResponse() {
        return this.protocol + " " + this.status.asString + "\r\n"
                + formatHeaders() + "\r\n" + this.body;
    }

    private String formatHeaders() {
        StringBuilder formattedHeaders = new StringBuilder();
        for (Map.Entry<HeaderField, String> header : this.headers.entrySet()) {
            formattedHeaders
                    .append(header.getKey().asString)
                    .append(header.getValue())
                    .append("\r\n");
        }
        return formattedHeaders.toString();
    }
}
