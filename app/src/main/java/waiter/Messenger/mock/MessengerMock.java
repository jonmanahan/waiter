package waiter.Messenger.mock;

import waiter.ClientConnection.Connectable;
import waiter.Messenger.Transportable;

public class MessengerMock implements Transportable {

    public Connectable calledWith;

    public void transport(Connectable clientConnectionMock) {
        this.calledWith = clientConnectionMock;
    }
}
