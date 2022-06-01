package waiter;

public class ThreadGeneratorMock implements Threadable{

    private final int numberOfThreadsToGenerate;
    public int numberOfThreadsGenerated;

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
