package waiter;

public class RouteDirector {

    public Route constructGetSimpleGet(RouteBuilder routeBuilder) {
        return routeBuilder.newUp()
                .url("/simple_get")
                .method("GET")
                .status("200 OK")
                .headers("Content-Length:")
                .body("")
                .build();
    }

    public Route constructHeadSimpleGet(RouteBuilder routeBuilder) {
        return routeBuilder.newUp()
                .url("/simple_get")
                .method("HEAD")
                .status("200 OK")
                .headers("Content-Length:")
                .body("")
                .build();
    }

    public Route constructNoMethodSimpleGet(RouteBuilder routeBuilder) {
        return routeBuilder.newUp()
                .url("/simple_get")
                .method("")
                .status("404 Not Found")
                .headers("Content-Length:")
                .body("")
                .build();
    }

    public Route constructGetSimpleGetWithBody(RouteBuilder routeBuilder) {
        return routeBuilder.newUp()
                .url("/simple_get_with_body")
                .method("GET")
                .status("200 OK")
                .headers("Content-Length:")
                .body("Hello world")
                .build();
    }

    public Route constructNoMethodSimpleGetWithBody(RouteBuilder routeBuilder) {
        return routeBuilder.newUp()
                .url("/simple_get_with_body")
                .method("GET")
                .status("200 OK")
                .headers("Content-Length:")
                .body("Hello world")
                .build();
    }

    public Route constructNoUrl(RouteBuilder routeBuilder) {
        return routeBuilder.newUp()
                .url("")
                .method("")
                .status("404 Not Found")
                .headers("Content-Length:")
                .body("")
                .build();
    }
}
