package waiter.ServerSocket;

import waiter.Socket.Socket;
import waiter.Socket.ClientSocket;

import java.io.IOException;

public record HostServerSocket(java.net.ServerSocket serverSocket) implements ServerSocket {

    public Socket accept() throws IOException {
        return new ClientSocket(this.serverSocket.accept());
    }

    public boolean isClosed() {
        return this.serverSocket.isClosed();
    }
}
