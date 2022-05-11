package waiter;

import waiter.ClientConnection.Connection;
import waiter.Listener.Awaiter;
import waiter.Messenger.Transporter;

import java.io.IOException;
import java.net.ServerSocket;

public class Communicator {

    private final Awaiter awaiter;
    private final Transporter transporter;
    private final int port;

    Communicator (Awaiter awaiter, Transporter transporter, int port) {
        this.awaiter = awaiter;
        this.transporter = transporter;
        this.port = port;
    }
    
    public void communicate() throws IOException {
        Connection connection = this.awaiter.awaitClient(new ServerSocket(this.port));

        this.transporter.transport(connection);
    }
}
