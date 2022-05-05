package waiter;


import java.io.IOException;

public interface Transporter {
    void transport(Connection client) throws IOException;
}
