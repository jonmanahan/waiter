package waiter.Messenger;

import waiter.ClientConnection.Connectable;
import waiter.EchoProtocol.Protocol;

import java.io.IOException;

public record Messenger(Protocol protocol) implements Transportable {

    public void transport(Connectable client) throws IOException {
        String fromClient = client.read();
        String toClient = this.protocol.serve(fromClient);
        client.write(toClient);
    }
}
