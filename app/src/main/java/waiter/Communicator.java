package waiter;

import java.io.IOException;

public class Communicator {
    void communicate(Awaiter server, Transporter messenger) throws IOException {
        Connection client = server.awaitClient();

        messenger.transport(client);
    }
}
