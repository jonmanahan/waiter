package waiter.Threadable.mock;

import waiter.Threadable.Threadable;

public class ThreadGeneratorMock implements Threadable {

    public int numberOfThreadsGenerated;

    public ThreadGeneratorMock() {
        this.numberOfThreadsGenerated = 0;
    }

    public void generate(Runnable runnable) {
        runnable.run();
        this.numberOfThreadsGenerated += 1;
    }
}
