package waiter.Communicator.mock;

import waiter.Communicator.Reportable;

import java.io.IOException;

public record CommunicatorMock(IOException exception) implements Reportable {

    public void communicate() throws IOException {
        throw this.exception;
    }
}