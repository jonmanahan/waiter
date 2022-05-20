package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import waiter.InputStreamer.InputStreamer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputStreamerTest {

    @Property(tries = 5)
    void readsInputUsingProvidedBufferReader(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        String readInput = new InputStreamer(new BufferedReader(new StringReader(userInput))).readLine();

        assertEquals(userInput, readInput);
    }
}
