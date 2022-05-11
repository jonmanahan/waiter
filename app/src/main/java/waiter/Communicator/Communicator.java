package waiter.Communicator;

import waiter.ClientConnection.Connection;
import waiter.Listener.Awaiter;
import waiter.Messenger.Transporter;

import java.io.IOException;
import java.net.ServerSocket;

public record Communicator(Awaiter awaiter, Transporter transporter, int port) implements Reporter {

    public void communicate() throws IOException {

        Connection connection = this.awaiter.awaitClient(new ServerSocket(this.port));

        this.transporter.transport(connection);
    }
}
