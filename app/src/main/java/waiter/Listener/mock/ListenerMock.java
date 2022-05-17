package waiter.Listener.mock;

import waiter.Listener.Awaitable;
import waiter.ClientConnection.Connectable;
import waiter.ClientConnection.mock.ClientConnectionMock;
import waiter.Reactor.Reactive;

public class ListenerMock implements Awaitable {

    String userInput;

    public ListenerMock(String userInput) {
        this.userInput = userInput;
    }

    public Connectable awaitClient(Reactive reactive) {
        return new ClientConnectionMock(this.userInput, 1);
    }
}
