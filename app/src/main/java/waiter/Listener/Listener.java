package waiter.Listener;

import waiter.ClientConnection.ClientConnection;
import waiter.ClientConnection.Connectable;
import waiter.InputStreamer.InputStreamer;
import waiter.OutputStreamer.OutputStreamer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

public class Listener implements Awaitable {

    public Connectable awaitClient(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();

        return new ClientConnection(
                new InputStreamer(new BufferedReader(new InputStreamReader(socket.getInputStream()))),
                new OutputStreamer(new PrintStream(socket.getOutputStream()))
        );
    }
}
