package waiter.Messenger;


import waiter.ClientConnection.Connection;

import java.io.IOException;

public interface Transporter {
    void transport(Connection client) throws IOException;
}
