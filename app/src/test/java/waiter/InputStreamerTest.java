package waiter;

import waiter.InputStreamer.InputStreamer;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputStreamerTest {

    @Test
    void readsInputUsingProvidedBufferReader() throws IOException {
        String userInput = "foo";
        String readInput = new InputStreamer(new BufferedReader(new StringReader(userInput))).readLine();
        assertEquals(userInput, readInput);
    }
}
