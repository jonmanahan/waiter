package waiter.Reportable;

import waiter.ServerSocket.ServerSocket;

import java.io.IOException;

public interface Reportable {

    void communicate(ServerSocket serverSocket) throws IOException;
}
