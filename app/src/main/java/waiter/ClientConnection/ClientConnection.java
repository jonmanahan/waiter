package waiter.ClientConnection;

import waiter.InputStreamer.Reader;
import waiter.Message;
import waiter.OutputStreamer.Writer;

import java.io.IOException;

public record ClientConnection(Reader reader, Writer writer) implements Connection {

    public Message read() throws IOException {
        String message = this.reader.readLine();
        return new Message(message);
    }

    public void write(Message toClient) throws IOException {
        writer.writeLine(toClient.open());
    }
}
