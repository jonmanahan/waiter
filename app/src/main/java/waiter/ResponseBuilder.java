package waiter;

public class ResponseBuilder {

    public String protocol = "HTTP/1.1";
    public String status = "200 OK";
    public String headers = "Content-Length:";
    public String body = "";

    public ResponseBuilder newUp() {
        return new ResponseBuilder();
    }

    public ResponseBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ResponseBuilder status(String status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder headers(String headers) {
        this.headers = headers;
        return this;
    }

    public ResponseBuilder body(String body) {
        this.body = body;
        return this;
    }

    public Response build() {
        this.headers = this.headers.replace("Content-Length:", "Content-Length: " + this.body.length());
        return new Response(this);
    }
}
