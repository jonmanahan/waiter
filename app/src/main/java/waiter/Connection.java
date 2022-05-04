package waiter;

public interface Connection {
    Message read();
    void write(Message toClient);
}
