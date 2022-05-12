package waiter.Listener;

import waiter.ClientConnection.Connectable;

import java.io.IOException;
import java.net.ServerSocket;

public interface Awaitable {

    Connectable awaitClient(ServerSocket serverSocket) throws IOException;
}
