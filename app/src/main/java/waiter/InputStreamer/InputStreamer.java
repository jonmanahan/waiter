package waiter.InputStreamer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import java.io.IOException;

public class InputStreamer implements Reader {

    private final Socket socket;

    public InputStreamer(Socket socket) {
        this.socket = socket;
    }

    public String readLine() throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( this.socket.getInputStream() ) );
        return bufferedReader.readLine();
    }
}
