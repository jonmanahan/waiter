package waiter.Readable.mock;

import waiter.Readable.Readable;

public record InputStreamerMock(String userInput) implements Readable {

    public String readLine() {
        return this.userInput;
    }
}
