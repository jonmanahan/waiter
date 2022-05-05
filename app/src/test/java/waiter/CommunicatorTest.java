package waiter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommunicatorTest {

    @Test
    void establishesCommunication() throws IOException {
        String userInput = "Doesn't matter whats here, simulating any user input message";
        ListenerMock listenerMock = new ListenerMock(userInput);
        MessengerMock messengerMock = new MessengerMock();
        Communicator communicator = new Communicator();
        communicator.communicate(listenerMock, messengerMock);
        Connection calledWithConnection = messengerMock.calledWith;
        assertEquals(userInput, calledWithConnection.read().open());
    }
}
