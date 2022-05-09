package waiter;

import java.io.IOException;

public interface Connection {
    Message read() throws IOException;

    void write(Message toClient) throws IOException;
}
