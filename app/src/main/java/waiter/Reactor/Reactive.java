package waiter.Reactor;

import waiter.Interactor.Interactive;

import java.io.IOException;

public interface Reactive {

    Interactive accept() throws IOException;

    boolean isClosed();
}
