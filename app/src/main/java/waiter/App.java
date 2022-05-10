/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package waiter;

import waiter.EchoProtocol.EchoProtocol;
import waiter.Listener.Listener;
import waiter.Messenger.Messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

public class App {

    public static void main(String[] args) {

        try {
            int port = 4424;
            Listener listener = new Listener(new ServerSocket(port));
            Messenger messenger = new Messenger(new EchoProtocol());
            Communicator communicator = new Communicator(listener, messenger);
            communicator.communicate();
        } catch (SocketException exception) {
            System.out.println("Sorry, connection could not be establish or has been broken, please try running the server and connecting again");
        } catch (IOException exception) {
            System.out.println("Sorry, there was a problem with your input, please try running the server and connecting again");
        }
    }
}
