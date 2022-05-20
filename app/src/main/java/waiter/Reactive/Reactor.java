package waiter.Reactive;

import waiter.Interactive.Interactive;
import waiter.Interactive.Interactor;

import java.io.IOException;
import java.net.ServerSocket;

public record Reactor(ServerSocket serverSocket) implements Reactive {

    public Interactive accept() throws IOException {
        return new Interactor(this.serverSocket.accept());
    }

    public boolean isClosed() {
        return this.serverSocket.isClosed();
    }
}
