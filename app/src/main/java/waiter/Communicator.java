package waiter;

public class Communicator {
    void communicate(Awaiter server, Transporter messenger){
        Connection client = server.awaitClient();

        messenger.transport(client);
    }
}
