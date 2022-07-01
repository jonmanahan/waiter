package waiter;

public class RequestParser {

    private static int currentRequestBodyLength;
    public static final String END_OF_LINE = System.lineSeparator();
    public static final String END_OF_HEADERS = System.lineSeparator() + System.lineSeparator();

    public Request parse(String requestMessage) {
        System.out.println(requestMessage);

        String requestStartLine = requestMessage.substring(
                0, getIndexAtDelimiter(requestMessage, END_OF_LINE)
        );

        String[] parsedRequestStartLine = requestStartLine.split(" ");
        String method = parsedRequestStartLine[0];
        String target = parsedRequestStartLine[1];
        String protocol = parsedRequestStartLine[2];

        String headers = requestMessage.substring(
                getIndexAfterDelimiter(requestMessage, END_OF_LINE),
                getIndexAtDelimiter(requestMessage, END_OF_HEADERS)
        );

        String body = requestMessage.substring(
                getIndexAfterDelimiter(requestMessage, END_OF_HEADERS)
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

        if(existsInRequestMessage(requestMessageBuilder, END_OF_HEADERS)) {
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
        String contentLengthHeader = "Content-Length: ";

        if(existsInRequestMessage(requestMessageBuilder, contentLengthHeader)) {
            String[] splitRequestMessage = requestMessageBuilder.toString().split(contentLengthHeader);
            contentLength = Integer.parseInt(
                    splitRequestMessage[1].substring(0, splitRequestMessage[1].indexOf(END_OF_LINE))
            );
        }

        return contentLength;
    }

    private static boolean existsInRequestMessage(StringBuilder requestMessageBuilder, String delimiter) {
        return requestMessageBuilder.indexOf(delimiter) != -1;
    }
}
