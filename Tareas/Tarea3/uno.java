public class Estrucutura implements Buffer<T>{
    BoundeQueue<T> e = new BoundeQueue<T>();
    ReentrantLock usarE = new ReentrantLock();
    Condition fullE = usarE.newCondition(); 
    Condition emptyE = usarE.newCondition();

    public void put(T item) {
        usarE.lock();
        while (e.isFull()) {
            fullE.await();
        }
        e.put(item);
        emptyE.signal();
        usarE.unlock();
    }

    public T get() {
        T item;
        usarE.lock();
        while(e.isEmpty()){
            emptyE.await();
        }
        item = e.get();
        fullE.signal();
        usarE.unlock();
        return item;
    }

    public int count(){
        return e.size();
    }
}