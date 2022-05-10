package waiter.OutputStreamer;

import java.io.PrintStream;
import java.net.Socket;

import java.io.IOException;

public class OutputStreamer implements Writer {

    private final Socket socket;

    public OutputStreamer(Socket socket) {
        this.socket = socket;
    }

    public void writeLine(String toClient) throws IOException {
        PrintStream printStream = new PrintStream( this.socket.getOutputStream() );
        printStream.println(toClient);
    }
}
