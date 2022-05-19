package waiter.ClientConnection;

import java.io.IOException;

public interface Connectable {

    String read() throws IOException;

    void write(String toClient) throws IOException;

    void close() throws IOException;
}

