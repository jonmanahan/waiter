package waiter;

import java.io.IOException;

public interface Reactive {

    Interactive accept() throws IOException;

    boolean isClosed();
}
