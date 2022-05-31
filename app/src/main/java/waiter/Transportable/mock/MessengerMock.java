package waiter.Transportable.mock;

import waiter.Connectable.Connectable;
import waiter.Transportable.Transportable;

public class MessengerMock implements Transportable {

    public Connectable calledWith;

    public void transport(Connectable clientConnectionMock) {
        this.calledWith = clientConnectionMock;
    }
}
