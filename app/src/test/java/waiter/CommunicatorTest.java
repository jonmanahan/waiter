package waiter;

import waiter.Communicator.Communicator;
import waiter.Listener.mock.ListenerMock;
import waiter.Messenger.mock.MessengerMock;
import waiter.Reactor.mock.ReactorMock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class CommunicatorTest {

    String clientHasDisconnected;

    @BeforeEach
    void setUp() {
        clientHasDisconnected = null;
    }

    @Test
    void communicatesProvidedInput() throws IOException {
        String[] clientRequests = {"curl foo1", "curl foo2", "curl foo3", "curl foo4"};
        ReactorMock reactor = new ReactorMock(clientRequests);

        new Communicator(
                new ListenerMock(new String[]{"foo", clientHasDisconnected}),
                new MessengerMock()
        ).communicate(reactor);

        assertEquals(clientRequests.length, reactor.numberOfAcceptedClients);
    }
}
