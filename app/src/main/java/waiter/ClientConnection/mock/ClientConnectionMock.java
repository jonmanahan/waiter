package waiter.ClientConnection.mock;

import waiter.ClientConnection.Connectable;

public class ClientConnectionMock implements Connectable {

    private final String userInput;
    public int currentNumberOfMessages;
    private int desiredNumberOfMessages;
    public String toClient;

    public ClientConnectionMock(String userInput, int desiredNumberOfMessages) {
        this.desiredNumberOfMessages = desiredNumberOfMessages;
        this.currentNumberOfMessages = 0;
        this.userInput = userInput;
        this.toClient = "";
    }

    public String read() {
        if(this.currentNumberOfMessages < this.desiredNumberOfMessages) {
            this.currentNumberOfMessages ++;
            return this.userInput;
        }
        else {
            return null;
        }
    }

    public void write(String toClient) {
        this.toClient = toClient;
    }

    public void close() {}
}

