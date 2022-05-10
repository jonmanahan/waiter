package waiter.ClientConnection.mock;

import waiter.ClientConnection.Connection;
import waiter.Message;

public class ClientConnectionMock implements Connection {
    private final String userInput;
    public String toClient;

    public ClientConnectionMock(String userInput) {
        this.userInput = userInput;
        this.toClient = "";
    }

    public Message read() {
        return new Message(this.userInput);
    }

    public void write(Message toClient) {
        this.toClient = toClient.open();
    }
}