package waiter.OutputStreamer;

import java.io.PrintStream;
import java.net.Socket;

import java.io.IOException;

public record OutputStreamer(Socket socket) implements Writer {

    public void writeLine(String toClient) throws IOException {
        PrintStream printStream = new PrintStream(this.socket.getOutputStream());
        printStream.println(toClient);
    }
}
