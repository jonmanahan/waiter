package waiter.Awaitable.mock;

import waiter.Awaitable.Awaitable;
import waiter.Connectable.Connectable;
import waiter.Connectable.mock.ClientConnectionMock;
import waiter.ServerSocket.ServerSocket;

import java.io.IOException;

public record ListenerMock(String[] userInputs) implements Awaitable {

    public Connectable awaitClient(ServerSocket serverSocket) throws IOException {
        serverSocket.accept();
        return new ClientConnectionMock(this.userInputs);
    }
}
