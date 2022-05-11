package waiter.Listener;

import waiter.ClientConnection.ClientConnection;
import waiter.ClientConnection.Connection;
import waiter.InputStreamer.InputStreamer;
import waiter.OutputStreamer.OutputStreamer;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

public class Listener implements Awaiter {

    public Connection awaitClient(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        return new ClientConnection(new InputStreamer(socket), new OutputStreamer(socket));
    }
}
