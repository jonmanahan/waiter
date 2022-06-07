package waiter.Reportable;

import waiter.Connectable.Connectable;
import waiter.Awaitable.Awaitable;
import waiter.Threadable.Threadable;
import waiter.Transportable.Transportable;
import waiter.ServerSocket.ServerSocket;

import java.io.IOException;

public record Communicator(Threadable threadable, Awaitable awaitable, Transportable transportable) implements Reportable {

    public void communicate(ServerSocket serverSocket) throws IOException {

        while(!serverSocket.isClosed()) {
            Connectable connectable = this.awaitable.awaitClient(serverSocket);

            this.threadable.generate(new RunnableTransport(this.transportable, connectable));
        }
    }

    public record RunnableTransport(Transportable transportable, Connectable connectable) implements Runnable {

        public void run() {
            try {
                this.transportable.transport(connectable);
                this.connectable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
