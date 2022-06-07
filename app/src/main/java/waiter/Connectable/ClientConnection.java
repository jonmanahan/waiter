package waiter.Connectable;

import waiter.Interactive.Interactive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ClientConnection implements Connectable {

    private final Interactive interactive;
    private final BufferedReader bufferedReader;
    private final PrintStream printStream;

    public ClientConnection(Interactive interactive) throws IOException {
        this.interactive = interactive;
        this.bufferedReader = new BufferedReader(new InputStreamReader(interactive.getInputStream()));
        this.printStream = new PrintStream(interactive.getOutputStream());
    }

    public String read() throws IOException {
        return this.bufferedReader.readLine();
    }

    public void write(String toClient) throws IOException {
        this.printStream.println(toClient);
    }

    public void close() throws IOException {
        this.interactive.close();
    }
}

