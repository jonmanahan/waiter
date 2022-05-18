package waiter.Reactor.mock;

import waiter.Interactor.Interactive;
import waiter.Interactor.mock.InteractorMock;
import waiter.Reactor.Reactive;

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
