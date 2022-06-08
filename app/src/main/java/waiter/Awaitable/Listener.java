package waiter.Awaitable;

import waiter.Connectable.ClientConnection;
import waiter.Connectable.Connectable;
import waiter.Socket.Socket;
import waiter.ServerSocket.ServerSocket;

import java.io.IOException;

public class Listener implements Awaitable {

    public Connectable awaitClient(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();

        return new ClientConnection(socket);
    }
}
