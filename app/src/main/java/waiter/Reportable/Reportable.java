package waiter.Reportable;

import waiter.Reactive.Reactive;

import java.io.IOException;

public interface Reportable {

    void communicate(Reactive reactive) throws IOException;
}
