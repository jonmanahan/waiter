package waiter.Messenger.mock;

import waiter.ClientConnection.Connection;
import waiter.Messenger.Transporter;

public class MessengerMock implements Transporter {

    public Connection calledWith;

    public void transport(Connection clientConnectionMock) {
        this.calledWith = clientConnectionMock;
    }
}
