package waiter;

import java.io.IOException;
import java.net.SocketException;

public class EchoServer {

    private final Reporter reporter;

    EchoServer (Reporter reporter) {
        this.reporter = reporter;
    }

    public void start() {

        try {
            reporter.communicate();
        } catch (SocketException exception) {
            System.out.println("Sorry, connection could not be established or has been broken, please try running the server and connecting again");
        } catch (IOException exception) {
            System.out.println("Sorry, there was a problem with your input, please try running the server and connecting again");
        }
    }
}
