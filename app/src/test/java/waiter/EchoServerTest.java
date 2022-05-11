package waiter;

import waiter.Listener.mock.ListenerMock;
import waiter.Messenger.mock.MessengerMock;

import org.junit.jupiter.api.Test;
import java.net.SocketException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EchoServerTest {

//    @Test
//    void throwsIOException() {
//        EchoServer echoServer = new EchoServer(new CommunicatorMock(new IOException()));
//        assertThrows(IOException.class, () -> {
//            echoServer.start();
//        });
//    }

    @Test
    void throwsSocketException() {
        EchoServer echoServer = new EchoServer(new CommunicatorMock(new SocketException()));
        assertThrows(SocketException.class, () -> {
            echoServer.start();
        });
    }
}
