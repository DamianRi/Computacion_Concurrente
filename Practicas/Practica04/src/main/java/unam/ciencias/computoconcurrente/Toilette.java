package unam.ciencias.computoconcurrente;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Toilette {
    private volatile long timesMalesEntered;
    private volatile long timesFemalesEntered;

    private Lock look = new ReentrantLock();

    private Condition menWaitingQueue = look.newCondition();
    private Condition womenWaitingQueue = look.newCondition();
    

   volatile int menCounterUsing;
   volatile int menCounterWaiting;
   volatile int womenCounterUsing;
   volatile int womenCounterWaiting;
 

    public Toilette() {
        this.timesMalesEntered = 0;
        this.timesFemalesEntered = 0;
    }


    public void enterMale() throws InterruptedException {
        look.lock();
        while(hayMuejeres() && womenCounterWaiting !=0){
            menCounterWaiting ++;
            menWaitingQueue.await();
            menCounterWaiting --;
        }
        womenCounterUsing ++;
        timesFemalesEntered++;

        look.unlock();

    }


    

    public void leaveMale(){
        look.lock();
        menCounterUsing--;
        if(menCounterUsing <= 0 && womenCounterWaiting != 0) menWaitingQueue.signal();
        look.unlock();
    }   

    public void enterFemale() throws InterruptedException {
        look.lock();
        while(hayHombres() && menCounterWaiting !=0){
            womenCounterWaiting++;
             womenWaitingQueue.await();
             womenCounterWaiting --;
        }
        
        timesFemalesEntered++;
        womenCounterUsing++;
        look.unlock();
   
    }
    

    public void leaveFemale(){

        look.lock();
        womenCounterUsing--;
        if(womenCounterUsing <= 0 && menCounterWaiting != 0) menWaitingQueue.signal();
        look.unlock();
    }

    public long getTimesMalesEntered() {
        return timesMalesEntered;
    }

    public long getTimesFemalesEntered() {
        return timesFemalesEntered;
    }

    public boolean hayMuejeres(){
        return womenCounterUsing > 0;
    }

    public boolean hayHombres(){
        return menCounterUsing > 0;
    }

    public void saleMujer(){
        this.womenCounterUsing --;

    }

    public void saleHombre(){
        this.menCounterUsing --;
    }

}