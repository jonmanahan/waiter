package waiter;

import java.io.IOException;
import java.net.ServerSocket;

public class Listener implements Awaiter {
    ServerSocket server;

    Listener(ServerSocket server) {
        this.server = server;
    }
    public Connection awaitClient() throws IOException {
        return new ClientConnection(this.server.accept());
    }
}
