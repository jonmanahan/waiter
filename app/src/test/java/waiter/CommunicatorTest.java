package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import waiter.Awaitable.mock.ListenerMock;
import waiter.Reactive.mock.ReactorMock;
import waiter.Reportable.Communicator;
import waiter.Transportable.mock.MessengerMock;

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
        int numberOfThreadsToGenerate = 1;
        String[] clientRequests = {"curl foo1", "curl foo2", "curl foo3", "curl foo4"};
        ReactorMock reactor = new ReactorMock(clientRequests);

        new Communicator(
                new ThreadGeneratorMock(numberOfThreadsToGenerate),
                new ListenerMock(new String[]{"foo", clientHasDisconnected}),
                new MessengerMock()
        ).communicate(reactor);

        assertEquals(clientRequests.length, reactor.numberOfAcceptedClients);
    }

    @Property
    void shouldAllowMultipleConnections(@ForAll @IntRange(min = 1, max = 10) Integer numberOfThreadsToGenerate) throws IOException {
        String[] clientRequests = {"curl foo1", "curl foo2", "curl foo3", "curl foo4"};
        ReactorMock reactor = new ReactorMock(clientRequests);
        ThreadGeneratorMock threadGeneratorMock = new ThreadGeneratorMock(numberOfThreadsToGenerate);

        new Communicator(
                threadGeneratorMock,
                new ListenerMock(new String[]{"foo", clientHasDisconnected}),
                new MessengerMock()
        ).communicate(reactor);

        assertEquals(clientRequests.length * numberOfThreadsToGenerate, reactor.numberOfAcceptedClients * threadGeneratorMock.numberOfThreadsGenerated);
    }
}
