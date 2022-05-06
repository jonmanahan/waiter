package waiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientConnection implements Connection {

    private final Socket client;

    ClientConnection(Socket client) {
        this.client = client;
    }

    public Message read() throws IOException {
        BufferedReader input = new BufferedReader( new InputStreamReader( this.client.getInputStream() ) );
        String message = input.readLine();
        return new Message(message);
    }

    public void write(Message toClient) throws IOException {
        PrintStream output = new PrintStream( client.getOutputStream() );
        output.println(toClient.open());
    }
}

