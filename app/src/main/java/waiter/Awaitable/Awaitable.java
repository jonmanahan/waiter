package waiter.Awaitable;

import waiter.Connectable.Connectable;
import waiter.ServerSocket.ServerSocket;

import java.io.IOException;

public interface Awaitable {

    Connectable awaitClient(ServerSocket serverSocket) throws IOException;
}
