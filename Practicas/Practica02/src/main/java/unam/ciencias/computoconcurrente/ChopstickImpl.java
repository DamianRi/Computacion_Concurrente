package unam.ciencias.computoconcurrente;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ChopstickImpl implements Chopstick {
    private int id;
    private AtomicInteger aTimes;
    private AtomicBoolean aBoolean; 
    private int timesTaken; 
    private boolean taken;

    public ChopstickImpl(int id) {
        this.id = id;
        this.aTimes = new AtomicInteger(0);
        this.timesTaken = aTimes.get();
        this.aBoolean = new AtomicBoolean(false);
        this.taken = aBoolean.get();
    }

    @Override
    public void take(){
        this.timesTaken = aTimes.incrementAndGet();
        while(this.taken);
        System.out.println("Tomando Chopstick ");
        this.aBoolean.set(true);
        this.taken = aBoolean.get();
    }   

    @Override
    public void release() {
        this.aBoolean.set(false);
        this.taken = aBoolean.get();
    }

    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public int getTimesTaken() {
        // TODO: Adjust with the right value
        //return 0;
        return timesTaken;
    }
}
