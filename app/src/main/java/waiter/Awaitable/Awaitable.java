package waiter.Awaitable;

import waiter.Connectable.Connectable;
import waiter.Reactive.Reactive;

import java.io.IOException;

public interface Awaitable {

    Connectable awaitClient(Reactive reactive) throws IOException;
}
