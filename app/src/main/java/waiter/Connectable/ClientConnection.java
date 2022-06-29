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
    private int currentRequestBodyLength;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printStream = new PrintStream(socket.getOutputStream());
        this.currentRequestBodyLength = 0;
    }

    public String read() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while(notEndOfRequest(stringBuilder)) {
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

    private boolean notEndOfRequest(StringBuilder requestMessageBuilder) {
        int requestBodyLength = 0;

        if(headersWereRead(requestMessageBuilder)) {
            requestBodyLength = getContentLength(requestMessageBuilder);
            currentRequestBodyLength += 1;
        }

        return currentRequestBodyLength <= requestBodyLength;
    }

    private int getContentLength(StringBuilder requestMessageBuilder) {
        int contentLength = 0;
        String[] splitRequestMessage = requestMessageBuilder.toString().split("Content-Length: ");

        if(contentLengthExists(splitRequestMessage)) {
            contentLength = Integer.parseInt(
                    splitRequestMessage[1].substring(0, splitRequestMessage[1].indexOf("\r\n"))
            );
        }

        return contentLength;
    }

    private boolean contentLengthExists(String[] contentLengthHeader) {
        return contentLengthHeader.length == 2;
    }

    private boolean headersWereRead(StringBuilder requestMessageBuilder) {
        return requestMessageBuilder.indexOf("\r\n\r\n") != -1;
    }
}

