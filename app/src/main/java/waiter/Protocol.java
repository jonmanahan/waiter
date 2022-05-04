package waiter;

public interface Protocol {
    public Message serve(Message fromClient);
}
