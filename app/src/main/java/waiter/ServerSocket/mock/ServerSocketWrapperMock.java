package waiter.ServerSocket.mock;

import waiter.Socket.Socket;
import waiter.Socket.mock.SocketWrapperMock;
import waiter.ServerSocket.ServerSocket;

public class ServerSocketWrapperMock implements ServerSocket {

    private final String[] clientRequests;
    public int numberOfAcceptedClients;

    public ServerSocketWrapperMock(String[] clientRequests) {
        this.clientRequests = clientRequests;
        this.numberOfAcceptedClients = 0;
    }

    public Socket accept() {
        this.numberOfAcceptedClients += 1;
        return new SocketWrapperMock();
    }

    public boolean isClosed() {
        return this.numberOfAcceptedClients >= this.clientRequests.length;
    }
}
