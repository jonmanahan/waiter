package waiter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.NotBlank;
import org.junit.jupiter.api.AfterEach;
import waiter.OutputStreamer.OutputStreamer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputStreamerTest {

    private final PrintStream originalSystemOut = System.out;

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Property(tries = 5)
    void writesOutputUsingProvidedPrintStream(@ForAll @AlphaChars @NotBlank String userInput) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        new OutputStreamer(printStream).writeLine(userInput);

        assertEquals(userInput, output.toString().trim());
    }
}

