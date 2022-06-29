package waiter.Connectable;

import waiter.Socket.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ClientConnection implements Connectable {

    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final PrintStream printStream;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printStream = new PrintStream(socket.getOutputStream());
    }

    public String read() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int currentRequestBodyLength = 0;
        while(notEndOfRequest(stringBuilder, currentRequestBodyLength)) {
            stringBuilder.append((char) this.bufferedReader.read());
        }

        return stringBuilder.toString();
    }

    public void write(String toClient) throws IOException {
        byte[] bytesToWriteToClient = toClient.getBytes(StandardCharsets.UTF_8);
        this.printStream.write(bytesToWriteToClient);
    }

    public void close() throws IOException {
        this.socket.close();
    }

    private boolean notEndOfRequest(StringBuilder requestMessageBuilder, int currentRequestBodyLength) {
        int requestBodyLength = 0;
        int headersEndIndex = requestMessageBuilder.indexOf("\r\n\r\n");

        if(headersWereRead(headersEndIndex)) {
            requestBodyLength = getContentLength(requestMessageBuilder, headersEndIndex);
            currentRequestBodyLength += 1;
        }

        return currentRequestBodyLength <= requestBodyLength;
    }

    private int getContentLength(StringBuilder requestMessageBuilder, int headersEndIndex) {
        int contentLength = 0;
        int contentLengthStartIndex = requestMessageBuilder.indexOf("Content-Length: ");

        if(contentLengthExists(contentLengthStartIndex)) {
            contentLength = Integer.parseInt(requestMessageBuilder.substring(contentLengthStartIndex + 1, headersEndIndex));
        }

        return contentLength;
    }

    private boolean contentLengthExists(int contentLengthStartIndex) {
        return contentLengthStartIndex != -1;
    }

    private boolean headersWereRead(int headersEndIndex) {
        return headersEndIndex != -1;
    }
}

