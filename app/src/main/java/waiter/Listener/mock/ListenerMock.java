package waiter.Listener.mock;

import waiter.Listener.Awaiter;
import waiter.ClientConnection.Connection;
import waiter.ClientConnection.mock.ClientConnectionMock;

import java.net.ServerSocket;

public class ListenerMock implements Awaiter {

    String userInput;

    public ListenerMock(String userInput) {
        this.userInput = userInput;
    }

    public Connection awaitClient(ServerSocket serverSocket) {
        return new ClientConnectionMock(this.userInput);
    }
}
