package waiter.Threadable;

public class ThreadGenerator implements Threadable {

    public void generate(Runnable runnable) {
        new Thread(runnable).start();
    }
}
