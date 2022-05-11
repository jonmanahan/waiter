package waiter.InputStreamer.mock;

import waiter.InputStreamer.Reader;

public record InputStreamerMock(String userInput) implements Reader {

    public String readLine() {
        return this.userInput;
    }
}
