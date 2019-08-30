package unam.ciencias.computoconcurrente;

public class FilterSemaphoreImpl implements Semaphore {

    private int totalThreads;
    private int permits;

    public FilterSemaphoreImpl(int totalThreads, int permits) {
        this.totalThreads = totalThreads;
        this.permits = permits;
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return permits;
    }

    @Override
    public void acquire() {
    }

    @Override
    public void release() {
    }
}
