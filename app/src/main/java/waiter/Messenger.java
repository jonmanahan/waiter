package waiter;

import java.io.IOException;

public class Messenger implements Transporter {

    Protocol protocol;

    Messenger(Protocol protocol) {
        this.protocol = protocol;
    }

    public void transport(Connection client) throws IOException {
        Message fromClient = client.read();
        Message toClient = this.protocol.serve(fromClient);
        client.write(toClient);
    }
}
