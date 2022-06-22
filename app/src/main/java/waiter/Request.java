package waiter;

public record Request(String url, String method, String protocol) {

    public enum Method {
        GET("GET"),
        HEAD("HEAD"),
        POST("POST")
        ;

        public final String asString;

        Method(final String requestMethod) {
            this.asString = requestMethod;
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }
}
