package waiter.Communicator;

import waiter.Reactive;

import java.io.IOException;

public interface Reportable {

    void communicate(Reactive reactive) throws IOException;
}
