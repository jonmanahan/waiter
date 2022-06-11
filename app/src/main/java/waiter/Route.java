package waiter;

public record Route(String protocol, String status, String headers, String body) {

    public String getProtocol() {
        return protocol;
    }

    public String getStatus() {
        return status;
    }

    public String getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
