package waiter.OutputStreamer;

import java.io.IOException;

public interface Writable {

    void writeLine(String toClient) throws IOException;
}