package waiter.InputStreamer.mock;

import waiter.InputStreamer.Readable;

public record InputStreamerMock(String userInput) implements Readable {

    public String readLine() {
        return this.userInput;
    }
}
