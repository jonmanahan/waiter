package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseBuilderTest {

    @Property
    void parsedRequestCreated(@ForAll @Size(4) List<@AlphaChars @NotBlank String> requestResults) {
        Route route = new Route(requestResults.get(0), requestResults.get(1), requestResults.get(2), requestResults.get(3));
        String expectedResponse = String.format(
                """
                %s %s
                %s
                
                %s""", requestResults.get(0), requestResults.get(1), requestResults.get(2), requestResults.get(3)).replace("\n", "\r\n");

        String response = new ResponseBuilder().buildResponse(route);

        assertEquals(expectedResponse, response);
    }
}
