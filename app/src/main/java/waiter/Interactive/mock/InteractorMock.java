package waiter.Interactive.mock;

import waiter.Interactive.Interactive;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InteractorMock implements Interactive {

    private final ByteArrayOutputStream byteOutputStream;
    private final ByteArrayInputStream byteInputStream;
    public boolean connectionClosed;

    public InteractorMock() {
        this.byteOutputStream = new ByteArrayOutputStream();
        this.byteInputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        this.connectionClosed = false;
    }
    public InteractorMock(ByteArrayOutputStream byteOutputStream) {
        this.connectionClosed = false;
        this.byteInputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));;
        this.byteOutputStream = byteOutputStream;
    }

    public InteractorMock(ByteArrayInputStream byteInputStream) {
        this.connectionClosed = false;
        this.byteInputStream = byteInputStream;
        this.byteOutputStream = new ByteArrayOutputStream();
    }


    public InputStream getInputStream() {
        return this.byteInputStream;
    }

    public OutputStream getOutputStream() {
        return this.byteOutputStream;
    }

    public void close() throws IOException {
        if(this.byteInputStream != null) {
            this.byteInputStream.close();
            this.byteOutputStream.close();
        }
        this.connectionClosed = true;
    }
}
