package waiter.Transportable;

import waiter.Connectable.Connectable;

import java.io.IOException;

public interface Transportable {

    void transport(Connectable client) throws IOException;
}
