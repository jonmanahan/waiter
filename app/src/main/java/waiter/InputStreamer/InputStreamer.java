package waiter.InputStreamer;

import java.io.BufferedReader;

import java.io.IOException;

public record InputStreamer(BufferedReader bufferedReader) implements Readable {

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
}
