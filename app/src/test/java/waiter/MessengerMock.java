package waiter;

public class MessengerMock implements Transporter {

    Connection calledWith;

    public void transport(Connection clientMock) {
        this.calledWith = clientMock;
    }
}
