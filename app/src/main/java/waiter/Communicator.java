package waiter;

import java.io.IOException;

public class Communicator {

    private final Awaiter awaiter;
    private final Transporter transporter;

    Communicator (Awaiter awaiter, Transporter transporter) {
        this.awaiter = awaiter;
        this.transporter = transporter;
    }
    
    void communicate() throws IOException {
        Connection connection = this.awaiter.awaitClient();

        this.transporter.transport(connection);
    }
}
