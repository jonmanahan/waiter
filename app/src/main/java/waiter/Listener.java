package waiter;

import java.io.IOException;
import java.net.ServerSocket;

public class Listener implements Awaiter {
    ServerSocket serverSocket;

    Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Connection awaitClient() throws IOException {
        return new ClientConnection(this.serverSocket.accept());
    }
}
