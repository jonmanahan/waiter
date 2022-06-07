package waiter;

import waiter.Reportable.Reportable;
import waiter.ServerSocket.ServerSocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

record EchoServer(Reportable reportable) {

    public void start(int port) {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            reportable.communicate(new ServerSocketWrapper(serverSocket));
        } catch (SocketException exception) {
            System.out.println("Sorry, connection could not be established or has been broken, please try running the server and connecting again");
        } catch (IOException exception) {
            System.out.println("Sorry, an error occurred when sending/receiving a message, please try running the server and connecting again");
        }
    }
}
