package waiter.Writable;

import java.io.PrintStream;

import java.io.IOException;

public record OutputStreamer(PrintStream printStream) implements Writable {

    public void writeLine(String toClient) throws IOException {
        printStream.println(toClient);
    }
}
