package waiter.OutputStreamer;

import java.io.PrintStream;
import java.net.Socket;

import java.io.IOException;

public record OutputStreamer(PrintStream printStream) implements Writer {

    public void writeLine(String toClient) throws IOException {
        printStream.println(toClient);
    }
}
