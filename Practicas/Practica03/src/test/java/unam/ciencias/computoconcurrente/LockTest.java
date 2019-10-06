package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockTest {
    static final int THREADS = 4;
    static final int ITERATIONS = 50;
    static final int MAX_VALUE = 1000000;
    static final int WORK_SIZE = MAX_VALUE / THREADS;
    static final int REMAINING_WORK = MAX_VALUE % THREADS;
    volatile Counter counter;
    Thread[] threads;

    @Test
    void tasLock() throws InterruptedException {
        performTest(new TASLock());
    }

    @Test
    void ttasLock() throws InterruptedException {
        performTest(new TTASLock());
    }

    @Test
    void backoffLock() throws InterruptedException {
        performTest(new BackoffLock());
    }

    @Test
    void clhLock() throws InterruptedException {
        performTest(new CLHLock());
    }

    @Test
    void mcsLock() throws InterruptedException {
        performTest(new MCSLock());
    }

    void performTest(Lock lock) throws InterruptedException {
        for(int i = 0; i < ITERATIONS; i++) {
            counter = new Counter(lock);
            threads = new Thread[THREADS];

            for(int j = 0, remainingWork = REMAINING_WORK; j < threads.length; j++, remainingWork--) {
                final int size = WORK_SIZE + (remainingWork>0 ? 1 : 0);
                threads[j] = new Thread(() -> incrementCounter(size));
            }

            for(Thread t : threads) {
                t.start();
            }

            for(Thread t : threads) {
                t.join();
            }

            assertEquals(MAX_VALUE, counter.getValue());
        }
    }

    void incrementCounter(final int iterations) {
        for(int i = 0; i < iterations; i++) {
            counter.getAndIncrement();
        }
    }
}

class Counter {
    volatile int value;
    volatile Lock lock;

    Counter(Lock lock) {
        this.value = 0;
        this.lock = lock;
    }
    int getAndIncrement() {
        this.lock.lock();
        int result = this.value++;
        this.lock.unlock();
        return result;
    }

    int getValue() {
        return value;
    }
}