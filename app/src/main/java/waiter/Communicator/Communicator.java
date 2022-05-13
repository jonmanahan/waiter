package waiter.Communicator;

import waiter.ClientConnection.Connectable;
import waiter.Listener.Awaitable;
import waiter.Messenger.Transportable;
import waiter.Reactive;

import java.io.IOException;

public record Communicator(Awaitable awaitable, Transportable transportable) implements Reportable {

    public void communicate(Reactive reactive) throws IOException {

        Connectable connectable = this.awaitable.awaitClient(reactive);

        while(!reactive.isClosed()) {
            this.transportable.transport(connectable);
        }
    }
}
