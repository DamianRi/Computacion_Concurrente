package unam.ciencias.computoconcurrente;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Math;
import java.lang.InterruptedException;

public class BackoffLock implements Lock {

    private AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY = 1000;
    private static final int MAX_DELAY = 2000;

    @Override
    public void lock() {
        try {
            Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
            while (true) {
                while (state.get()) {
                }
                if (!state.getAndSet(true)) {
                    return;
                } else {
                    backoff.backoff();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
    }

    @Override
    public void unlock() {
        state.set(false);
    }
}

class Backoff {
    final int minDelay;
    final int maxDelay;
    int limit;
    final Random random;

    public Backoff(int min, int max) {
        minDelay = min;
        maxDelay = min;
        limit = minDelay;
        random = new Random();
    }

    public void backoff() throws InterruptedException {
        int delay = random.nextInt(limit);
        limit = Math.min(maxDelay, 2 * limit);
        Thread.sleep(delay);
    }

}