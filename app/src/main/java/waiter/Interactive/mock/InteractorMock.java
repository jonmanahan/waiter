package waiter.Interactive.mock;

import waiter.Interactive.Interactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InteractorMock implements Interactive {

    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    public boolean connectionClosed;

    public InteractorMock() {
        this.connectionClosed = false;
    }


    public InputStream getInputStream() {
        inputStream = new InputStream() {
            public int read() {
                return -1;
            }
        };

        return inputStream;
    }

    public OutputStream getOutputStream() {
        outputStream = new OutputStream() {
            public void write(int b) {

            }
        };

        return outputStream;
    }

    public void close() throws IOException {
        if(inputStream != null) {
            inputStream.close();
            outputStream.close();
        }
        this.connectionClosed = true;
    }
}
