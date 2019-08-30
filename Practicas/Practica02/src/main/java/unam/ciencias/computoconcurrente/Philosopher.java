package unam.ciencias.computoconcurrente;

/**
 * Each Philosopher must be runnable in the context of a thread, then we need to implement the Runnable interface.
 */
public abstract class Philosopher implements Runnable {
    public static int DEFAULT_TABLE_SIZE = 5;

    protected int id;
    protected int eatingCount;
    protected Chopstick leftChopstick;
    protected Chopstick rightChopstick;

    public Philosopher() {
        this.eatingCount = 0;
    }

    @Override
    public void run() {
        try {
            while(true) {
                think();
                enterTable();
            }
        }
        catch (InterruptedException ie) {
            System.out.printf("Thread %s finished execution eating %d times\n", Thread.currentThread().getName(),
                    eatingCount);
        }
    }

    public void enterTable() throws InterruptedException {
        try {
            takeChopsticks();
            eat();
        }
        catch (InterruptedException ie) {
            throw ie;
        }
        finally {
            leaveChopsticks();
        }

    }

    /**
     * The philosopher must stop a few milliseconds to think which chopstick to take first.
     */
    public void think() throws InterruptedException {
        long millis = getRandomTime();
        Thread.sleep(millis);
    }

    /**
     * The philosopher takes both chopsticks, one by one.
     */
    public abstract void takeChopsticks();

    /**
     * Once the philosopher took both chopsticks he can start eating. It must stop for a few milliseconds to finish
     * eating. This is the critical section.
     */
    public void eat() throws InterruptedException {
        eatingCount++;
        long millis = getRandomTime();
        Thread.sleep(millis);
    }

    /**
     * Once the philosopher finished eating he finally leaves both chopsticks on the table so that other philosophers
     * may use them to eat. He releases the chopsticks one by one.
     */
    public abstract void leaveChopsticks();

    /**
     * Returns the identifier of this philosopher
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Return any value between 0 and 10
     * @return
     */
    private long getRandomTime() {
        return Double.valueOf(Math.random() * 10).longValue();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEatingCount() {
        return eatingCount;
    }

    public void setLeftChopstick(Chopstick leftChopstick) {
        this.leftChopstick = leftChopstick;
    }

    public void setRightChopstick(Chopstick rightChopstick) {
        this.rightChopstick = rightChopstick;
    }

    public Chopstick getLeftChopstick() {
        return leftChopstick;
    }

    public Chopstick getRightChopstick() {
        return rightChopstick;
    }
}
