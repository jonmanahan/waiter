package waiter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Interactive {

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    void close() throws IOException;
}