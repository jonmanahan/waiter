package waiter.Protocol;

import java.util.Arrays;
import java.util.Objects;

public class HttProtocol implements Protocol {

    public String serve(String fromClient) {
        System.out.println(fromClient);
        String[] validURLs = {"/simple_get", "/simple_get_with_body"};

        String[] parsedRequestStartLine = fromClient.split(" ");
        String method = parsedRequestStartLine[0];
        String target = parsedRequestStartLine[1];
        String response = parsedRequestStartLine[2];

        if(Objects.equals(method, "GET") && Arrays.asList(validURLs).contains(target)) {
            response += " 200 OK\nConnection: close\n\n";
        }
        if(Objects.equals(target, "/simple_get_with_body")) {
            response += "Hello world";
        }

        return response;
    }
}
