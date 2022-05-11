package waiter.Messenger;

import waiter.ClientConnection.Connection;
import waiter.EchoProtocol.Protocol;
import waiter.Message;

import java.io.IOException;

public record Messenger(Protocol protocol) implements Transporter {

    public void transport(Connection client) throws IOException {
        Message fromClient = client.read();
        Message toClient = this.protocol.serve(fromClient);
        client.write(toClient);
    }
}
