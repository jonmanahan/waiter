package waiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseBuilderTest {

    @Test
    void responseProperlyFormatted() {
        String protocol = "PROTOCOL", headers = "HEADERS", body = "BODY";
        Response.Status status = Response.Status.OK;
        Response response = new ResponseBuilder()
                .newUp()
                .protocol(protocol)
                .status(status)
                .headers(headers)
                .body(body)
                .build();

        String formattedResponse = response.formatResponse();

        String expectedResponse = String.format(
                """
                %s %s
                %s
                
                %s""", protocol, status.asString, headers, body).replace("\n", "\r\n");
        assertEquals(expectedResponse, formattedResponse);
    }
}
