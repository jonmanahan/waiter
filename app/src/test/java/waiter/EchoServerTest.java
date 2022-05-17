package waiter;

import waiter.Communicator.mock.CommunicatorMock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.net.SocketException;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {

    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    void catchesIOException() {
        CommunicatorMock communicatorMock = new CommunicatorMock(true, new IOException());
        EchoServer echoServer = new EchoServer(communicatorMock);
        echoServer.start(4424);
        assertEquals("Sorry, an error occurred when sending/receiving a message, please try running the server and connecting again", output.toString().trim());
        assertFalse(communicatorMock.communicateWasCalled);
    }

    @Test
    void catchesSocketException() {
        CommunicatorMock communicatorMock = new CommunicatorMock(true, new SocketException());
        EchoServer echoServer = new EchoServer(communicatorMock);
        echoServer.start(4424);
        assertEquals("Sorry, connection could not be established or has been broken, please try running the server and connecting again", output.toString().trim());
        assertFalse(communicatorMock.communicateWasCalled);
    }

    // This is to cover lack of integration or E2E tests, could be replaced when these test type coverages are introduced
    @Test
    void callsCommunicate() {
        CommunicatorMock communicatorMock = new CommunicatorMock(false, new IOException());
        EchoServer echoServer = new EchoServer(communicatorMock);
        echoServer.start(4424);
        assertTrue(communicatorMock.communicateWasCalled);
    }
}
