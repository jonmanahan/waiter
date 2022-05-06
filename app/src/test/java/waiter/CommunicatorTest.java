package waiter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommunicatorTest {

    @Test
    void establishesCommunication() throws IOException {
        String userInput = "foo";
        ListenerMock listenerMock = new ListenerMock(userInput);
        MessengerMock messengerMock = new MessengerMock();
        Communicator communicator = new Communicator(listenerMock, messengerMock);
        communicator.communicate();
        Connection calledWithConnection = messengerMock.calledWith;
        assertEquals(userInput, calledWithConnection.read().open());
    }
}
