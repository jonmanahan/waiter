package waiter;

import waiter.ClientConnection.Connection;
import waiter.Communicator.Communicator;
import waiter.Listener.mock.ListenerMock;
import waiter.Messenger.mock.MessengerMock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class CommunicatorTest {

    @Test
    void establishesCommunication() throws IOException {
        ListenerMock listenerMock = new ListenerMock("foo");
        MessengerMock messengerMock = new MessengerMock();
        Communicator communicator = new Communicator(listenerMock, messengerMock, 4424);
        communicator.communicate();
        Connection clientConnectionMock = messengerMock.calledWith;
        assertEquals("foo", clientConnectionMock.read().open());
    }
}
