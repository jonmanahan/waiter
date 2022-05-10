package waiter.Listener;

import waiter.ClientConnection.Connection;

import java.io.IOException;

public interface Awaiter {
    Connection awaitClient() throws IOException;
}
