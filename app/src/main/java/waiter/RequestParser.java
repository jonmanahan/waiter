package waiter;

public class RequestParser {

    private static int currentRequestBodyLength;

    public Request parse(String requestMessage) {
        System.out.println(requestMessage);

        String endOfStartLineDelimiter = "\r\n";
        String endOfHeadersDelimiter = "\r\n\r\n";
        System.out.println(requestMessage.indexOf(endOfStartLineDelimiter));

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

    public static boolean notEndOfRequest(StringBuilder requestMessageBuilder) {
        int requestBodyLength = 0;

        if(headersWereRead(requestMessageBuilder)) {
            requestBodyLength = getContentLength(requestMessageBuilder);
            currentRequestBodyLength += 1;
        }
        else {
            currentRequestBodyLength = 0;
        }

        return currentRequestBodyLength <= requestBodyLength;
    }

    private static int getContentLength(StringBuilder requestMessageBuilder) {
        int contentLength = 0;
        String[] splitRequestMessage = requestMessageBuilder.toString().split("Content-Length: ");

        if(contentLengthExists(splitRequestMessage)) {
            contentLength = Integer.parseInt(
                    splitRequestMessage[1].substring(0, splitRequestMessage[1].indexOf("\r\n"))
            );
        }

        return contentLength;
    }

    private static boolean contentLengthExists(String[] contentLengthHeader) {
        return contentLengthHeader.length == 2;
    }

    private static boolean headersWereRead(StringBuilder requestMessageBuilder) {
        return requestMessageBuilder.indexOf("\r\n\r\n") != -1;
    }
}
