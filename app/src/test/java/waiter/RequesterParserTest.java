package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequesterParserTest {

    @Property
    void parsedRequestCreated(@ForAll @Size(3) List<@AlphaChars @NotBlank String> startLineParts) {
        RequestParser requestParser = new RequestParser();
        String startLine = startLineParts.get(0) + " " + startLineParts.get(1) + " " + startLineParts.get(2);

        Request request = requestParser.parse(startLine);

        assertEquals(request.getMethod(), startLineParts.get(0));
        assertEquals(request.getUrl(), startLineParts.get(1));
        assertEquals(request.getProtocol(), startLineParts.get(2));
    }
}
