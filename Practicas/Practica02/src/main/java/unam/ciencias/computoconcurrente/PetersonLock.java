package unam.ciencias.computoconcurrente;

import java.util.HashMap;
import java.util.Map;

/**
 * This particular implementation of a lock uses the Peterson's Algorithm which only work for two concurrent threads at
 * most.
 */
public class PetersonLock implements Lock {
   
    private boolean[] flag = new boolean[2];
    private int victim;

    public PetersonLock() {
    }

    @Override
    public void lock() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) {};
    }

    @Override
    public void unlock() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        flag[i] = false;
    }
}
