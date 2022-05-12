package waiter.ClientConnection;

import waiter.Message;

import java.io.IOException;

public interface Connectable {

    Message read() throws IOException;

    void write(Message toClient) throws IOException;
}