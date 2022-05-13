package waiter.Listener;

import waiter.ClientConnection.ClientConnection;
import waiter.ClientConnection.Connectable;
import waiter.InputStreamer.InputStreamer;
import waiter.Interactive;
import waiter.OutputStreamer.OutputStreamer;
import waiter.Reactive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.io.IOException;

public class Listener implements Awaitable {

    public Connectable awaitClient(Reactive reactive) throws IOException {
        Interactive interactive = reactive.accept();

        return new ClientConnection(
                new InputStreamer(new BufferedReader(new InputStreamReader(interactive.getInputStream()))),
                new OutputStreamer(new PrintStream(interactive.getOutputStream()))
        );
    }
}
