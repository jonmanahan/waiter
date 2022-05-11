package waiter.InputStreamer.mock;

import waiter.InputStreamer.Reader;

public class InputStreamerMock implements Reader {

    private final String userInput;

    public InputStreamerMock(String userInput) {
        this.userInput = userInput;
    }

    public String readLine() {
        return this.userInput;
    }
}
