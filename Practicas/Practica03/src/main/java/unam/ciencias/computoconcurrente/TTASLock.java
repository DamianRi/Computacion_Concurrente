package unam.ciencias.computoconcurrente;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock {

    AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while (true) {
            while(state.get()){}//Esperamos
            if (!state.getAndSet(true)) {
                return;
            }            
        }
    }

    @Override
    public void unlock() {
        state.set(false);
    }
}
