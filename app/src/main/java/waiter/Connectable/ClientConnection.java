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
        StringBuilder requestContents = new StringBuilder();
        while(!requestContents.toString().contains("\r\n")) {
            requestContents.append((char) this.bufferedReader.read());
        }

        return requestContents.toString().strip();
    }

    public void write(String toClient) throws IOException {
        this.printStream.write(toClient.getBytes(StandardCharsets.UTF_8));
    }

    public void close() throws IOException {
        this.socket.close();
    }
}

