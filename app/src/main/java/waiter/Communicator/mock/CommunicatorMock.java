package waiter.Communicator.mock;

import waiter.Communicator.Reporter;

import java.io.IOException;

public record CommunicatorMock(IOException exception) implements Reporter {

    public void communicate() throws IOException {
        throw this.exception;
    }
}