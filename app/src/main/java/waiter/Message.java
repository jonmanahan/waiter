package waiter;

public class Message {
    String content;

    Message(String content) {
        this.content = content;
    }

    String open() {
        return this.content;
    }
}
