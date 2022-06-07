package waiter.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public record SocketWrapper(java.net.Socket socket) implements Socket {

    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    public void close() throws IOException {
        this.socket.close();
    }
}
