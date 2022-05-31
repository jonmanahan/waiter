package waiter.Reactive;

import waiter.Interactive.Interactive;

import java.io.IOException;

public interface Reactive {

    Interactive accept() throws IOException;

    boolean isClosed();
}
