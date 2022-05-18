package waiter.Listener.mock;

import waiter.Listener.Awaitable;
import waiter.ClientConnection.Connectable;
import waiter.ClientConnection.mock.ClientConnectionMock;
import waiter.Reactor.Reactive;

import java.io.IOException;

public record ListenerMock(String[] userInputs) implements Awaitable {

    public Connectable awaitClient(Reactive reactive) throws IOException {
        reactive.accept();
        return new ClientConnectionMock(this.userInputs);
    }
}
