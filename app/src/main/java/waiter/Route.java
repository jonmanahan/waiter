package waiter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Route {

    private final AbstractMap.SimpleEntry<String, Map<String, Map<String, String>>> route;

    public Route(RouteBuilder routeBuilder) {
        this.route =  new AbstractMap.SimpleEntry<>(
                routeBuilder.url,
                new HashMap<>(){{
                        put(routeBuilder.method, routeBuilder.responseFields);
                }}
        );
    }

    public String getKey() {
        return route.getKey();
    }

    public Map<String, Map<String, String>> getValue() {
        return route.getValue();
    }
}
