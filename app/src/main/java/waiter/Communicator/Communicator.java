package waiter.Communicator;

import waiter.ClientConnection.Connectable;
import waiter.Listener.Awaitable;
import waiter.Messenger.Transportable;
import waiter.Reactor.Reactive;

import java.io.IOException;

public record Communicator(Awaitable awaitable, Transportable transportable) implements Reportable {

    public void communicate(Reactive reactive) throws IOException {

        while(!reactive.isClosed()) {
            Connectable connectable = this.awaitable.awaitClient(reactive);

            this.transportable.transport(connectable);
            connectable.close();
        }
    }
}
