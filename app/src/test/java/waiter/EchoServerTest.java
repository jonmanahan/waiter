package waiter;

import waiter.Communicator.mock.CommunicatorMock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.net.SocketException;

public class EchoServerTest {

    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    void catchesIOException() {
        EchoServer echoServer = new EchoServer(new CommunicatorMock(new IOException()));
        echoServer.start();
        assertEquals("Sorry, there was a problem with your input, please try running the server and connecting again", output.toString().trim());
    }

    @Test
    void catchesSocketException() {
        EchoServer echoServer = new EchoServer(new CommunicatorMock(new SocketException()));
        echoServer.start();
        assertEquals("Sorry, connection could not be established or has been broken, please try running the server and connecting again", output.toString().trim());
    }
}
