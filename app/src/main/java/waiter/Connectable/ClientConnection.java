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
        while(notEndOfLine(stringBuilder)) {
            stringBuilder.append((char) this.bufferedReader.read());
        }

        return stringBuilder.toString().strip();
    }

    public void write(String toClient) throws IOException {
        byte[] bytesToWriteToClient = toClient.getBytes(StandardCharsets.UTF_8);
        this.printStream.write(bytesToWriteToClient);
    }

    public void close() throws IOException {
        this.socket.close();
    }

    private boolean notEndOfLine(StringBuilder requestStartLineBuilder) {
        return requestStartLineBuilder.indexOf("\r\n") == -1;
    }
}

