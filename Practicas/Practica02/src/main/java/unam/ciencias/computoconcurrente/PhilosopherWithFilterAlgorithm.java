package unam.ciencias.computoconcurrente;

public class PhilosopherWithFilterAlgorithm extends Philosopher {


    private Semaphore semaphore;
    //private FilterSemaphoreImpl fsi;
    //private ChopstickImpl[] palillos;
    //Philosopher[] philosophers;
    public PhilosopherWithFilterAlgorithm() {
        super();
        semaphore = new FilterSemaphoreImpl(5, 4);
        /*
        fsi = new FilterSemaphoreImpl(5, 4);
        palillos = new ChopstickImpl[5];
        for (int i = 0; i < 5; i++) {
            palillos[i] = new ChopstickImpl(i);
        }
        */
        

    }

    @Override
    public void enterTable() throws InterruptedException {
        try {

            // TODO: Do something to restrict access to enter table, at most 4 philosophers must enter the critical section
            //fsi.acquire();
            super.enterTable();
        }
        catch (InterruptedException ie) {
            throw ie;
        }
        finally {
            // TODO: Ensure that you properly notify all threads that you are leaving the critical section
            //fsi.release();
        }
    }

    @Override
    public void takeChopsticks() {
        /*

        int i = Integer.parseInt(Thread.currentThread().getName());                
        palillos[i].take();
        palillos[i+1%5].take();
        */
        leftChopstick.take();
        rightChopstick.take();

    }

    @Override
    public void leaveChopsticks() {
        leftChopstick.release();
        rightChopstick.release();
    }
}
