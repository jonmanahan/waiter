package waiter;

import org.junit.jupiter.api.Test;

import waiter.Response.HeaderField;
import waiter.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseBuilderTest {

    @Test
    void responseProperlyFormatted() {
        String protocol = "PROTOCOL", headers = "HEADERS", body = "BODY";
        Status status = Status.OK;
        HeaderField field = HeaderField.Allow;
        Response response = new ResponseBuilder()
                .newUp()
                .protocol(protocol)
                .status(status)
                .headers(field, headers)
                .body(body)
                .build();

        String formattedResponse = response.formatResponse();
        String formattedHeaders = field.asString + headers + "\n" + HeaderField.ContentLength.asString + body.length();

        String expectedResponse = String.format(
                """
                %s %s
                %s
                
                %s""", protocol, status.asString, formattedHeaders, body).replace("\n", "\r\n");
        assertEquals(expectedResponse, formattedResponse);
    }
}
