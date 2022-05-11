package waiter.InputStreamer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import java.io.IOException;

public record InputStreamer(Socket socket) implements Reader {

    public String readLine() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        return bufferedReader.readLine();
    }
}
