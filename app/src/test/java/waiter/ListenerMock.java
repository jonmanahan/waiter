package waiter;

public class ListenerMock implements Awaiter {

    String userInput;

    ListenerMock(String userInput) {
        this.userInput = userInput;
    }

    public Connection awaitClient() {
        return new ClientConnectionMock(this.userInput);
    }
}
