package waiter.ClientConnection;

import waiter.Message;
import waiter.Reader;
import waiter.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

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

