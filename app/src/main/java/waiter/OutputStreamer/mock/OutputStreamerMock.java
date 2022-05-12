package waiter.OutputStreamer.mock;

import waiter.OutputStreamer.Writable;

public class OutputStreamerMock implements Writable {

    public String writtenOutput;

    public OutputStreamerMock() {
        this.writtenOutput = "";
    }

    public void writeLine(String toClient) {
        this.writtenOutput = toClient;
    }
}
