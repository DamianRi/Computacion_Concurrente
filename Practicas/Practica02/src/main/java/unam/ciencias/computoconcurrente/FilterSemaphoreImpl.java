package unam.ciencias.computoconcurrente;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class FilterSemaphoreImpl implements Semaphore {

    private int totalThreads;
    private int permits;
    private int count;
    private volatile int[] level;
    private volatile int[] victim;
    
    /* Con mutex
    private ReentrantLock mutex;
    private Condition condition;
    */
    public FilterSemaphoreImpl(int totalThreads, int permits) {
        this.totalThreads = totalThreads;
        this.permits = permits;
        this.count = totalThreads-permits+1;

        level = new int[totalThreads];
        victim = new int[count];

        /* Con Mutex
        mutex = new ReentrantLock();
        condition = mutex.newCondition();
        */
        

    }

    @Override
    public int getPermitsOnCriticalSection() {
        return permits;
    }

    @Override
    public void acquire(){     
        
        //Lock del filtro
        int i = Integer.parseInt(Thread.currentThread().getName());
        for(int l = 1; l < count; l++){
            level[i] = l; // Marca que está interesado
            victim[l] = i; // Se hace la vistima del nivel

            //verificamos si existe un k que cumpla lo del while
            for (int k = 0 ; k < totalThreads; k++) {
                while( (k != i && (level[k] >= l)) && victim[l] == i ){ 
                    //Esperamos mucho tiempo
                }
            }
        }
        
        /* Con mutex
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
        */
    }

    @Override
    public void release() {
                
        int i = Integer.parseInt(Thread.currentThread().getName());
        level[i] = 0; //Se desinteresa

        /* Con mutex
        mutex.lock();
        count--;
        condition.signalAll();
        mutex.unlock();
        */
    }
}
