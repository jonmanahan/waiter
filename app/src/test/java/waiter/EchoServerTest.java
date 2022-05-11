package waiter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServerTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
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
