package waiter.Reactor.mock;

import waiter.Interactor.Interactive;
import waiter.Interactor.mock.InteractorMock;
import waiter.Reactor.Reactive;

public class ReactorMock implements Reactive {

    private final int desiredNumberOfEchos;
    public int currentNumberOfEchos;

    public ReactorMock(int desiredNumberOfEchos) {
        this.desiredNumberOfEchos = desiredNumberOfEchos;
        this.currentNumberOfEchos = 0;
    }

    public Interactive accept() {
        return new InteractorMock();
    }

    public boolean isClosed() {
        if(this.currentNumberOfEchos >= this.desiredNumberOfEchos) {
            return true;
        }
        this.currentNumberOfEchos += 1;
        return false;
    }
}
