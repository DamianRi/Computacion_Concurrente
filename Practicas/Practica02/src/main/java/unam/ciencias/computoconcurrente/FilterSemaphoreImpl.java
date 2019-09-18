package unam.ciencias.computoconcurrente;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class FilterSemaphoreImpl implements Semaphore {

    private int totalThreads;
    private int permits;
    private int count;
    private AtomicInteger[] level;
    private AtomicInteger[] victim;
    private ReentrantLock mutex;
    private Condition condition;
    public FilterSemaphoreImpl(int totalThreads, int permits) {
        this.totalThreads = totalThreads;
        this.permits = permits;
        this.count = 0;

        level = new AtomicInteger[permits];
        victim = new AtomicInteger[permits];

        for(int i = 1; i < permits; i++){
            level[i] = new AtomicInteger();
            victim[i] = new AtomicInteger();
        }

        mutex = new ReentrantLock();
        condition = mutex.newCondition();
        

    }

    @Override
    public int getPermitsOnCriticalSection() {
        return permits;
    }

    @Override
    public void acquire(){     
        try{
            mutex.lock();
            while(count == permits){
                condition.await();
            }
            count++;
            mutex.unlock();
        }catch (InterruptedException e){
            e.toString();
        }
        /*

        //Lock del filtro
        int i = Integer.parseInt(Thread.currentThread().getName());
        for(int l = 1; l < permits; l++){
            level[i].set(l);
            victim[l].set(i);
            for (int k = 0 ; k < permits; k++) {
                while( (k != i && (level[k].get() >= l)) && victim[l].get() == i ){ // 
                    //Esperamos mucho tiempo
                }
            }
        }
        
        count++;
        level[i].set(0);
        */
    }

    @Override
    public void release() {
        mutex.lock();
        count--;
        condition.signalAll();
        mutex.unlock();
        /*

        int i = Integer.parseInt(Thread.currentThread().getName());
        for(int l = 1; l < permits; l++){
            level[i].set(l);
            victim[l].set(i);
            for (int k = 0 ; k < permits; k++) {
                while((k != i) && (level[k].get() >= l && victim[l].get() == i) ){ // 
                    //Esperamos mucho tiempo
                }
            }
        }
        
        int i = Integer.parseInt(Thread.currentThread().getName());
        
        count--;
        level[i].set(0);
        */
    }
}
