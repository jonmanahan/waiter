/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package waiter;

import waiter.Awaitable.Listener;
import waiter.Protocol.HttProtocol;
import waiter.Reportable.Communicator;
import waiter.Threadable.ThreadGenerator;
import waiter.Transportable.Messenger;

import java.util.function.Function;

class App {

    public static void main(String[] args) {

        int port = 5000;
        Listener listener = new Listener();
        Routes routes = constructRoutes();
        HttProtocol protocol = new HttProtocol(
                new RequestParser(),
                new Router(routes)
        );
        Messenger messenger = new Messenger(protocol);
        ThreadGenerator threadGenerator = new ThreadGenerator();
        EchoServer echoServer = new EchoServer(new Communicator(threadGenerator, listener, messenger));
        echoServer.start(port);
    }

    private static Routes constructRoutes() {
        Routes routes = new Routes();

        routes.addRoute(
                new Route("/simple_get", new Request.Method[]{Request.Method.GET, Request.Method.HEAD}, okHandler)
        );

        routes.addRoute(
                new Route("/simple_get_with_body", new Request.Method[]{Request.Method.GET}, okWithOnlyResponseBodyHandler)
        );

        routes.addRoute(
                new Route("/head_request", new Request.Method[]{Request.Method.HEAD, Request.Method.OPTIONS}, okHandler)
        );

        routes.addRoute(
                new Route("/echo_body", new Request.Method[]{Request.Method.POST}, okWithRequestBodyHandler)
        );

        return routes;
    }

    public static final Function<Request, Response> okHandler = request -> new ResponseBuilder()
            .newUp()
            .build();

    private static final Function<Request, Response> okWithOnlyResponseBodyHandler = request -> new ResponseBuilder()
            .newUp()
            .body("Hello world")
            //.headers(HeaderField.ContentType, "application/x-www-form-urlencoded")
            .build();

    private static final Function<Request, Response> okWithRequestBodyHandler = request -> new ResponseBuilder()
            .newUp()
            .body(request.getBody())
            //.headers(HeaderField.ContentType, "application/x-www-form-urlencoded")
            .build();
}
