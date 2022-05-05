package waiter;

import java.io.IOException;

public class Messenger implements Transporter {

    Protocol server;

    Messenger(Protocol server) {
        this.server = server;
    }

    public void transport(Connection client) throws IOException {
        Message fromClient = client.read();
        Message toClient = this.server.serve(fromClient);
        client.write(toClient);
    }
}
