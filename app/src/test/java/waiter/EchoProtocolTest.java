package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import waiter.EchoProtocol.EchoProtocol;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoProtocolTest {

    @Property(tries = 5)
    void messageGivenBack(@ForAll @AlphaChars @NotBlank String fromClient) {
        EchoProtocol echoProtocol = new EchoProtocol();
        String response = echoProtocol.serve(fromClient);
        assertEquals(response, fromClient);
    }
}
