package waiter.Reportable;

import waiter.Connectable.Connectable;
import waiter.Awaitable.Awaitable;
import waiter.Transportable.Transportable;
import waiter.Reactive.Reactive;

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
