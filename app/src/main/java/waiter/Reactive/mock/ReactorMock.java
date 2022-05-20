package waiter.Reactive.mock;

import waiter.Interactive.Interactive;
import waiter.Interactive.mock.InteractorMock;
import waiter.Reactive.Reactive;

public class ReactorMock implements Reactive {

    private final String[] clientRequests;
    public int numberOfAcceptedClients;

    public ReactorMock(String[] clientRequests) {
        this.clientRequests = clientRequests;
        this.numberOfAcceptedClients = 0;
    }

    public Interactive accept() {
        this.numberOfAcceptedClients += 1;
        return new InteractorMock();
    }

    public boolean isClosed() {
        return this.numberOfAcceptedClients >= this.clientRequests.length;
    }
}
