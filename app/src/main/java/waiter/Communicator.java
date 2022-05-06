package waiter;

import java.io.IOException;

public class Communicator {

    private final Awaiter server;
    private final Transporter messenger;

    Communicator (Awaiter server, Transporter messenger) {
        this.server = server;
        this.messenger = messenger;
    }
    
    void communicate() throws IOException {
        Connection client = this.server.awaitClient();

        this.messenger.transport(client);
    }
}
