package waiter.Messenger;

import waiter.ClientConnection.Connectable;
import waiter.EchoProtocol.Protocol;
import waiter.Message;

import java.io.IOException;

public record Messenger(Protocol protocol) implements Transportable {

    public void transport(Connectable client) throws IOException {
        Message fromClient = client.read();
        Message toClient = this.protocol.serve(fromClient);
        client.write(toClient);
    }
}
