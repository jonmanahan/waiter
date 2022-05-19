package waiter;

import waiter.OutputStreamer.OutputStreamer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputStreamerTest {

    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(output);

    @BeforeEach
    void setUp() {
        System.setOut(printStream);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    void writesOutputUsingProvidedPrintStream() throws IOException {
        String userInput = "foo";
        new OutputStreamer(printStream).writeLine(userInput);
        assertEquals(userInput, output.toString().trim());
    }
}

