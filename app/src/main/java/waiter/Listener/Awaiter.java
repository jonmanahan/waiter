package waiter.Listener;

import waiter.ClientConnection.Connection;

import java.io.IOException;
import java.net.ServerSocket;

public interface Awaiter {

    Connection awaitClient(ServerSocket serverSocket) throws IOException;
}
