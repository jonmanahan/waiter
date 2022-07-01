package waiter;

import org.junit.jupiter.api.Test;

import waiter.Response.HeaderField;
import waiter.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static waiter.RequestParser.END_OF_HEADERS;
import static waiter.RequestParser.END_OF_LINE;

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
        String formattedHeaders = field.asString + "HEADERS" + END_OF_LINE + HeaderField.ContentLength.asString + body.length();

        String expectedResponse = protocol + " " + Status.OK.asString + END_OF_LINE + formattedHeaders + END_OF_HEADERS + body;

        assertEquals(expectedResponse, formattedResponse);
    }
}
