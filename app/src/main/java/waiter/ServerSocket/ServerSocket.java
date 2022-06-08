package waiter.ServerSocket;

import waiter.Socket.Socket;

import java.io.IOException;

public interface ServerSocket {

    Socket accept() throws IOException;

    boolean isClosed();
}
