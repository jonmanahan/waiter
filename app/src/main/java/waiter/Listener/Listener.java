package waiter.Listener;

import waiter.ClientConnection.ClientConnection;
import waiter.ClientConnection.Connection;
import waiter.InputStreamer;
import waiter.OutputStreamer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Awaiter {
    ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Connection awaitClient() throws IOException {
        Socket socket = this.serverSocket.accept();
        InputStreamer inputStreamer = new InputStreamer(socket);
        OutputStreamer outputStreamer = new OutputStreamer(socket);
        return new ClientConnection(inputStreamer, outputStreamer);
    }
}
