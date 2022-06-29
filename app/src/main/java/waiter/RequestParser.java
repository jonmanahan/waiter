package waiter;

public class RequestParser {

    public Request parse(String requestMessage) {

        String endOfStartLineDelimiter = "\r\n";
        String endOfHeadersDelimiter = "\r\n\r\n";

        String requestStartLine = requestMessage.substring(
                0, getIndexAtDelimiter(requestMessage, endOfStartLineDelimiter)
        );

        String[] parsedRequestStartLine = requestStartLine.split(" ");
        String method = parsedRequestStartLine[0];
        String target = parsedRequestStartLine[1];
        String protocol = parsedRequestStartLine[2];

        String headers = requestMessage.substring(
                getIndexAfterDelimiter(requestMessage, endOfStartLineDelimiter),
                getIndexAtDelimiter(requestMessage, endOfHeadersDelimiter)
        );

        String body = requestMessage.substring(
                getIndexAfterDelimiter(requestMessage, endOfHeadersDelimiter)
        );

        return new Request(target, method, protocol, headers, body);
    }

    private int getIndexAfterDelimiter(String requestMessage, String delimiter) {
        return requestMessage.indexOf(delimiter) + delimiter.length();
    }

    private int getIndexAtDelimiter(String requestMessage, String delimiter) {
        return requestMessage.indexOf(delimiter);
    }
}
