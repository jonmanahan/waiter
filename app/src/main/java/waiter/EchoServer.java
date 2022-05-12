package waiter;

import waiter.Communicator.Reporter;

import java.io.IOException;
import java.net.SocketException;

public record EchoServer(Reporter reporter) {

    public void start() {

        try {
            reporter.communicate();
        } catch (SocketException exception) {
            System.out.println("Sorry, connection could not be established or has been broken, please try running the server and connecting again");
        } catch (IOException exception) {
            System.out.println("Sorry, an error occurred when sending/receiving a message, please try running the server and connecting again");
        }
    }
}
