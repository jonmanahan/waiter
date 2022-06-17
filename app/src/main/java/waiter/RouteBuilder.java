package waiter;

import java.util.Map;

public class RouteBuilder {

    public String url;
    public String method;
    public String status;
    public String headers;
    public String body;
    public Map<String, String> responseFields;

    public RouteBuilder newUp() {
        return new RouteBuilder();
    }

    public RouteBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RouteBuilder method(String method) {
        this.method = method;
        return this;
    }

    public RouteBuilder status(String status) {
        this.status = status;
        return this;
    }

    public RouteBuilder headers(String headers) {
        this.headers = headers;
        return this;
    }

    public RouteBuilder body(String body) {
        this.body = body;
        return this;
    }

    private void responseFields() {

        this.responseFields = Map.ofEntries(
                Map.entry("status", this.status),
                Map.entry("headers", this.headers.replace("Content-Length:", "Content-Length: " + this.body.length())),
                Map.entry("body", this.body)
        );
    }

    public Route build() {
        responseFields();
        return new Route(this);
    }
}
