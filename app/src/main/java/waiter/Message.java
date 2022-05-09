package waiter;

public class Message {
    String content;

    public Message(String content) {
        this.content = content;
    }

    public String open() {
        return this.content;
    }
}
