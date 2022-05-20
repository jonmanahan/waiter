package waiter.Awaitable.mock;

import waiter.Awaitable.Awaitable;
import waiter.Connectable.Connectable;
import waiter.Connectable.mock.ClientConnectionMock;
import waiter.Reactive.Reactive;

import java.io.IOException;

public record ListenerMock(String[] userInputs) implements Awaitable {

    public Connectable awaitClient(Reactive reactive) throws IOException {
        reactive.accept();
        return new ClientConnectionMock(this.userInputs);
    }
}
