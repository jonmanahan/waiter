package waiter.Listener.mock;

import waiter.Listener.Awaitable;
import waiter.ClientConnection.Connectable;
import waiter.ClientConnection.mock.ClientConnectionMock;

import java.net.ServerSocket;

public class ListenerMock implements Awaitable {

    String userInput;

    public ListenerMock(String userInput) {
        this.userInput = userInput;
    }

    public Connectable awaitClient(ServerSocket serverSocket) {
        return new ClientConnectionMock(this.userInput);
    }
}
