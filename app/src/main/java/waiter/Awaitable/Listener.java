package waiter.Awaitable;

import waiter.Connectable.ClientConnection;
import waiter.Connectable.Connectable;
import waiter.Interactive.Interactive;
import waiter.Reactive.Reactive;

import java.io.IOException;

public class Listener implements Awaitable {

    public Connectable awaitClient(Reactive reactive) throws IOException {
        Interactive interactive = reactive.accept();

        return new ClientConnection(interactive);
    }
}
