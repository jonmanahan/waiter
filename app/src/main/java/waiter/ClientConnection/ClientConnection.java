package waiter.ClientConnection;

import waiter.InputStreamer.Readable;
import waiter.Message;
import waiter.OutputStreamer.Writable;

import java.io.IOException;

public record ClientConnection(Readable readable, Writable writable) implements Connectable {

    public Message read() throws IOException {
        String message = this.readable.readLine();
        return new Message(message);
    }

    public void write(Message toClient) throws IOException {
        writable.writeLine(toClient.open());
    }
}
