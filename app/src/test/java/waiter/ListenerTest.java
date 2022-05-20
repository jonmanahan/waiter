package waiter;

import waiter.Connectable.Connectable;
import waiter.Awaitable.Listener;
import waiter.Reactive.mock.ReactorMock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.IOException;

public class ListenerTest {

    @Test
    void clientConnectionCreated() throws IOException {
        Listener listener = new Listener();
        ReactorMock reactorMock = new ReactorMock(new String[]{"curl foo1"});
        assertInstanceOf(Connectable.class, listener.awaitClient(reactorMock));
    }
}
