package waiter;

import java.util.HashMap;
import java.util.Map;

public class Routes {

    public Map<String, Map<String, Map<String, String>>> routes;

    public Routes() {
        this.routes = new HashMap<>();
    }

    private void addRoute(Route route) {
        this.routes.putIfAbsent(route.getKey(), route.getValue());
        if(this.routes.containsKey(route.getKey())) {
            this.routes.get(route.getKey()).putAll(route.getValue());
        }
    }

    public Routes constructRoutes() {
        RouteDirector routeDirector = new RouteDirector();
        RouteBuilder routeBuilder = new RouteBuilder();

        this.addRoute(routeDirector.constructGetSimpleGet(routeBuilder));
        this.addRoute(routeDirector.constructHeadSimpleGet(routeBuilder));
        this.addRoute(routeDirector.constructNoMethodSimpleGet(routeBuilder));
        this.addRoute(routeDirector.constructGetSimpleGetWithBody(routeBuilder));
        this.addRoute(routeDirector.constructNoMethodSimpleGetWithBody(routeBuilder));
        this.addRoute(routeDirector.constructNoUrl(routeBuilder));

        return this;
    }

    public Map<String, String> getRequestedResults(String url, String method) {
        String noUrl = "", noMethod = "";
        Map<String, Map<String, String>> methodAssociatedUrls = this.routes.getOrDefault(url, this.routes.get(noUrl));
        return methodAssociatedUrls.getOrDefault(method, methodAssociatedUrls.get(noMethod));
    }
}
