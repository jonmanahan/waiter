package waiter.Communicator.mock;

import waiter.Communicator.Reporter;

import java.io.IOException;

public class CommunicatorMock implements Reporter {

    private final IOException exception;

    CommunicatorMock(IOException exception) {
        this.exception = exception;
    }

    public void communicate() throws IOException {
        throw this.exception;
    }
}