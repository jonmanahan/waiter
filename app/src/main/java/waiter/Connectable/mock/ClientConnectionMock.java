package waiter.Connectable.mock;

import waiter.Connectable.Connectable;

public class ClientConnectionMock implements Connectable {

    private final String[] clientInputs;
    private int inputIndex;
    public String[] echoedInputs;

    public ClientConnectionMock(String[] clientInputs) {
        this.clientInputs = clientInputs;
        this.echoedInputs = new String[clientInputs.length - 1];
        this.inputIndex = 0;
    }

    public String read() {
        return this.clientInputs[this.inputIndex];
    }

    public void write(String toClient) {
        this.echoedInputs[this.inputIndex] = this.clientInputs[this.inputIndex];
        this.inputIndex ++;
    }

    public void close() {}
}

