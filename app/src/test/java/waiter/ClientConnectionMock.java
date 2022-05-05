package waiter;

public class ClientConnectionMock implements Connection {
    private final String userInput;
    String toClient;

    ClientConnectionMock(String userInput) {
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
