package waiter;

public interface Protocol {
    Message serve(Message fromClient);
}
