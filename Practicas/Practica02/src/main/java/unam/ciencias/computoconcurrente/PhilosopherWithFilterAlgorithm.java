package unam.ciencias.computoconcurrente;

public class PhilosopherWithFilterAlgorithm extends Philosopher {


    private Semaphore semaphore;

    public PhilosopherWithFilterAlgorithm() {
        super();
    }

    public void enterTable() throws InterruptedException {
        try {
            // TODO: Do something to restrict access to enter table, at most 4 philosophers must enter the critical section
            super.enterTable();
        }
        catch (InterruptedException ie) {
            throw ie;
        }
        finally {
            // TODO: Ensure that you properly notify all threads that you are leaving the critical section
        }
    }

    @Override
    public void takeChopsticks() {
    }

    @Override
    public void leaveChopsticks() {
    }
}
