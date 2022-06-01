package waiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import waiter.Awaitable.mock.ListenerMock;
import waiter.Transportable.mock.MessengerMock;
import waiter.Reactive.mock.ReactorMock;
import waiter.Reportable.Communicator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommunicatorTest {

    String clientHasDisconnected;

    @BeforeEach
    void setUp() {
        // Needs to be null because reading the input stream returns null when the sockets are disconnected
        // which signals the client is disconnected
        clientHasDisconnected = null;
    }

    @Test
    void shouldAllowSequentialConnections() throws IOException {
        int numberOfThreads = 1;
        String[] clientRequests = {"curl foo1", "curl foo2", "curl foo3", "curl foo4"};
        ReactorMock reactor = new ReactorMock(clientRequests);

        new Communicator(
                new ThreadGeneratorMock(numberOfThreads),
                new ListenerMock(new String[]{"foo", clientHasDisconnected}),
                new MessengerMock()
        ).communicate(reactor);

        assertEquals(clientRequests.length, reactor.numberOfAcceptedClients);
    }

    @Test
    void shouldAllowMultipleConnections() throws IOException {
        int numberOfThreads = 2;
        String[] clientRequests = {"curl foo1", "curl foo2", "curl foo3", "curl foo4"};
        ReactorMock reactor = new ReactorMock(clientRequests);

        new Communicator(
                new ThreadGeneratorMock(numberOfThreads),
                new ListenerMock(new String[]{"foo", clientHasDisconnected}),
                new MessengerMock()
        ).communicate(reactor);

        assertEquals(clientRequests.length * numberOfThreads, reactor.numberOfAcceptedClients);
    }
}
