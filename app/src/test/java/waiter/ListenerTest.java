package waiter;

import org.junit.jupiter.api.Test;
import waiter.ClientConnection.Connectable;
import waiter.Listener.Listener;
import waiter.Reactor.mock.ReactorMock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ListenerTest {

    @Test
    void clientConnectionCreated() throws IOException {
        Listener listener = new Listener();
        ReactorMock reactorMock = new ReactorMock(0);
        assertInstanceOf(Connectable.class, listener.awaitClient(reactorMock));
    }
}
