package waiter;

import waiter.Communicator.Reportable;
import waiter.Reactor.Reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

record EchoServer(Reportable reportable) {

    public void start(int port) {

        try {
            reportable.communicate(new Reactor(new ServerSocket(port)));
        } catch (SocketException exception) {
            System.out.println("Sorry, connection could not be established or has been broken, please try running the server and connecting again");
        } catch (IOException exception) {
            System.out.println("Sorry, an error occurred when sending/receiving a message, please try running the server and connecting again");
        }
    }
}
