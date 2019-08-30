package unam.ciencias.computoconcurrente;

public interface Chopstick {

    /**
     * This chopstick is taken, consider that up to two philosophers might try to get this chopstick at the same time.
     * One must take it while the other must wait for it to be released to take it.
     */
    void take();
    /**
     * This chopstick is release such that philosophers waiting on take must be able to proceed.
     */
    void release();
    /**
     * Returns the identifier of this chopstick.
     */
    int getId();
    /**
     * Returns how many times this chopstick has been taken
     */
    int getTimesTaken();
}
