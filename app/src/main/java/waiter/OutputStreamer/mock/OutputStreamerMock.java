package waiter.OutputStreamer.mock;

import waiter.OutputStreamer.Writer;

public class OutputStreamerMock implements Writer {

    public String writtenOutput;

    public OutputStreamerMock() {
        this.writtenOutput = "";
    }

    public void writeLine(String toClient) {
        this.writtenOutput = toClient;
    }
}
