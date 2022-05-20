package waiter.Connectable;

import waiter.Readable.Readable;
import waiter.Interactive.Interactive;
import waiter.Writable.Writable;

import java.io.IOException;

public record ClientConnection(Interactive interactive, Readable readable, Writable writable) implements Connectable {

    public String read() throws IOException {
        return this.readable.readLine();
    }

    public void write(String toClient) throws IOException {
        writable.writeLine(toClient);
    }

    public void close() throws IOException {
        this.interactive.close();
    }
}

