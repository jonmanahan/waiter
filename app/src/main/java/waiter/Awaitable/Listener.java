package waiter.Awaitable;

import waiter.Connectable.ClientConnection;
import waiter.Connectable.Connectable;
import waiter.Readable.InputStreamer;
import waiter.Interactive.Interactive;
import waiter.Writable.OutputStreamer;
import waiter.Reactive.Reactive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.io.IOException;

public class Listener implements Awaitable {

    public Connectable awaitClient(Reactive reactive) throws IOException {
        Interactive interactive = reactive.accept();

        return new ClientConnection(
                interactive,
                new InputStreamer(new BufferedReader(new InputStreamReader(interactive.getInputStream()))),
                new OutputStreamer(new PrintStream(interactive.getOutputStream()))
        );
    }
}
