package waiter;

public class ThreadGeneratorMock {

    private final int numberOfThreadsToGenerate;
    private int numberOfThreadsGenerated;

    ThreadGeneratorMock(int numberOfThreadsToGenerate) {
        this.numberOfThreadsToGenerate = numberOfThreadsToGenerate;
        this.numberOfThreadsGenerated = 0;
    }

    public void generate(Runnable runnable) {
        while(this.numberOfThreadsGenerated < this.numberOfThreadsToGenerate) {
            runnable.run();
            this.numberOfThreadsGenerated += 1;
        }
    }
}
