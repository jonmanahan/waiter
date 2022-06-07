package waiter.ServerSocket;

import waiter.Socket.Socket;
import waiter.Socket.SocketWrapper;

import java.io.IOException;

public record ServerSocketWrapper(java.net.ServerSocket serverSocket) implements ServerSocket {

    public Socket accept() throws IOException {
        return new SocketWrapper(this.serverSocket.accept());
    }

    public boolean isClosed() {
        return this.serverSocket.isClosed();
    }
}
