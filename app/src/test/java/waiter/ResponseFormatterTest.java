package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseFormatterTest {

    @Property
    void responseProperlyFormatted(@ForAll @Size(4) List<@AlphaChars @NotBlank String> requestResults) {
        Response Response = new Response(requestResults.get(0), requestResults.get(1), requestResults.get(2), requestResults.get(3));
        String expectedResponse = String.format(
                """
                %s %s
                %s
                
                %s""", requestResults.get(0), requestResults.get(1), requestResults.get(2), requestResults.get(3)).replace("\n", "\r\n");

        String response = new ResponseFormatter().formatResponse(Response);

        assertEquals(expectedResponse, response);
    }
}
