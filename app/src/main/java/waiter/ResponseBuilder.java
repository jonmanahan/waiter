package waiter;

import java.util.TreeMap;

public class ResponseBuilder {

    public String protocol = "HTTP/1.1";
    public Response.Status status = Response.Status.OK;
    public TreeMap<Response.HeaderField, String> headers = new TreeMap<>();
    public String body = "";

    public ResponseBuilder newUp() {
        return new ResponseBuilder();
    }

    public ResponseBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ResponseBuilder status(Response.Status status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder headers(Response.HeaderField headerField, String value) {
        this.headers.put(headerField, value);
        return this;
    }

    public ResponseBuilder body(String body) {
        this.body = body;
        return this;
    }

    public Response build() {
        this.headers.put(Response.HeaderField.ContentLength, String.valueOf(this.body.length()));
        return new Response(this);
    }
}
