package waiter.ServerSocket.mock;

import waiter.Socket.Socket;
import waiter.Socket.mock.ClientSocketMock;
import waiter.ServerSocket.ServerSocket;

public class HostServerSocketMock implements ServerSocket {

    private final String[] clientRequests;
    public int numberOfAcceptedClients;

    public HostServerSocketMock(String[] clientRequests) {
        this.clientRequests = clientRequests;
        this.numberOfAcceptedClients = 0;
    }

    public Socket accept() {
        this.numberOfAcceptedClients += 1;
        return new ClientSocketMock();
    }

    public boolean isClosed() {
        return this.numberOfAcceptedClients >= this.clientRequests.length;
    }
}
