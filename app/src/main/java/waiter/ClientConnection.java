package waiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientConnection implements Connection {

    private final Socket clientSocket;

    ClientConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Message read() throws IOException {
        BufferedReader input = new BufferedReader( new InputStreamReader( this.clientSocket.getInputStream() ) );
        String message = input.readLine();
        return new Message(message);
    }

    public void write(Message toClient) throws IOException {
        PrintStream output = new PrintStream( clientSocket.getOutputStream() );
        output.println(toClient.open());
    }
}

