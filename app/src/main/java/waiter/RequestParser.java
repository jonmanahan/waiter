package waiter;

public class RequestParser {

    public Request parse(String startLine) {

        String[] parsedRequestStartLine = startLine.split(" ");
        String method = parsedRequestStartLine[0];
        String target = parsedRequestStartLine[1];
        String protocol = parsedRequestStartLine[2];
        return new Request(target, method, protocol);
    }
}
