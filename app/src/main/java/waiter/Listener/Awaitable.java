package waiter.Listener;

import waiter.ClientConnection.Connectable;
import waiter.Reactive;

import java.io.IOException;

public interface Awaitable {

    Connectable awaitClient(Reactive reactive) throws IOException;
}
