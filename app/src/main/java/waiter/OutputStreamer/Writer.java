package waiter.OutputStreamer;

import java.io.IOException;

public interface Writer {

    void writeLine(String toClient) throws IOException;
}
