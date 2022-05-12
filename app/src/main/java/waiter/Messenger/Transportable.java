package waiter.Messenger;

import waiter.ClientConnection.Connectable;

import java.io.IOException;

public interface Transportable {

    void transport(Connectable client) throws IOException;
}
