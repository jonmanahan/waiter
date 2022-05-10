package waiter.ClientConnection;

import waiter.InputStreamer.Reader;
import waiter.Message;
import waiter.OutputStreamer.Writer;

import java.io.IOException;

public class ClientConnection implements Connection {

    private final Reader reader;
    private final Writer writer;

    public ClientConnection(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Message read() throws IOException {
        String message = this.reader.readLine();
        return new Message(message);
    }

    public void write(Message toClient) throws IOException {
        writer.writeLine(toClient.open());
    }
}
