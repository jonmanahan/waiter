package waiter.Communicator;

import waiter.Reactor.Reactive;

import java.io.IOException;

public interface Reportable {

    void communicate(Reactive reactive) throws IOException;
}
