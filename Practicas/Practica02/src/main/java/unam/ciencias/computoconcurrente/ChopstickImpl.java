package unam.ciencias.computoconcurrente;

public class ChopstickImpl implements Chopstick {
    private int id;

    public ChopstickImpl(int id) {
        this.id = id;
    }

    @Override
    public void take() {
    }

    @Override
    public void release() {
    }

    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public int getTimesTaken() {
        // TODO: Adjust with the right value
        return 0;
    }
}
