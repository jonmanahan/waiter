package waiter.Communicator.mock;

import waiter.Communicator.Reportable;
import waiter.Reactor.Reactive;


import java.io.IOException;

public class CommunicatorMock implements Reportable {

    private final boolean throwException;
    private final IOException exception;
    public boolean communicateWasCalled;

    public CommunicatorMock(boolean throwException, IOException exception) {
        this.communicateWasCalled = false;
        this.throwException = throwException;
        this.exception = exception;
    }

    public void communicate(Reactive reactive) throws IOException {
        if(throwException) {
            throw this.exception;
        }
        else {
            this.communicateWasCalled = true;
        }
    }
}