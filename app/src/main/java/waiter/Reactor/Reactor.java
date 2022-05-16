package waiter.Reactor;

import waiter.Interactor.Interactive;
import waiter.Interactor.Interactor;

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
