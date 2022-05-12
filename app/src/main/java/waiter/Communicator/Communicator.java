package waiter.Communicator;

import waiter.ClientConnection.Connectable;
import waiter.Listener.Awaitable;
import waiter.Messenger.Transportable;

import java.io.IOException;
import java.net.ServerSocket;

public record Communicator(Awaitable awaitable, Transportable transportable, int port) implements Reportable {

    public void communicate() throws IOException {

        Connectable connectable = this.awaitable.awaitClient(new ServerSocket(this.port));

        this.transportable.transport(connectable);
    }
}
