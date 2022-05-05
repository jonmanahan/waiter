package waiter;

import java.io.IOException;

public interface Awaiter {
    Connection awaitClient() throws IOException;
}
