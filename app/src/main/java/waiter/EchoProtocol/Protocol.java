package waiter.EchoProtocol;

import waiter.Message;

public interface Protocol {
    Message serve(Message fromClient);
}
