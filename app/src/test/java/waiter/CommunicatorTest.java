package waiter;

import waiter.ClientConnection.Connectable;
import waiter.Communicator.Communicator;
import waiter.Listener.mock.ListenerMock;
import waiter.Messenger.mock.MessengerMock;

import org.junit.jupiter.api.Test;
import waiter.Reactor.mock.ReactorMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class CommunicatorTest {

    @Test
    void communicatesProvidedInput() throws IOException {
        ListenerMock listenerMock = new ListenerMock("foo");
        MessengerMock messengerMock = new MessengerMock();
        Communicator communicator = new Communicator(listenerMock, messengerMock);
        ReactorMock reactor = new ReactorMock(5);
        communicator.communicate(reactor);
        Connectable clientConnectionMock = messengerMock.calledWith;
        assertEquals("foo", clientConnectionMock.read().open());
        assertEquals(reactor.currentNumberOfEchos, 5);
    }
}
