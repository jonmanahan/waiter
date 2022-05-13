package waiter.Communicator.mock;

import waiter.Communicator.Reportable;
import waiter.Reactive;


import java.io.IOException;

public record CommunicatorMock(IOException exception) implements Reportable {

    public void communicate(Reactive reactive) throws IOException {
        throw this.exception;
    }
}