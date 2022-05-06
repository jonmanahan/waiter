package waiter;

public class MessengerMock implements Transporter {

    Connection calledWith;

    public void transport(Connection clientConnectionMock) {
        this.calledWith = clientConnectionMock;
    }
}
