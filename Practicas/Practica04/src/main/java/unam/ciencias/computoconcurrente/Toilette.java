package unam.ciencias.computoconcurrente;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Toilette {
    private volatile long timesMalesEntered;
    private volatile long timesFemalesEntered;

    private Lock look = new ReentrantLock();

    private Condition womenWaitingQueue = look.newCondition();
    private Condition menWaitingQueue = look.newCondition();

    private final int BATHROOM_CAPACITY = 20;
    private int free_resources = BATHROOM_CAPACITY;

   int menCounterUsing;
   int womenCounterUsing;
   int menCounterWaiting;
   int womenCounterWaiting;


    public Toilette() {
        this.timesMalesEntered = 0;
        this.timesFemalesEntered = 0;
    }


    public void enterMale(){
        look.lock();
        
        try{
        if(free_resources != 0 && womenCounterUsing != 0){
            menCounterWaiting ++;
            menWaitingQueue.await();
        }   
            free_resources --;
            menCounterWaiting --;
            timesMalesEntered ++;
            menCounterUsing ++;
            
        

        }catch(InterruptedException e){
            look.unlock();
        }

        look.unlock();
    }


    

    public void leaveMale(){
        look.lock();
        free_resources ++;
        menCounterUsing--;
        if(womenCounterWaiting != 0){
            womenWaitingQueue.signal();
        }else{
            menWaitingQueue.signal();
        }
        look.unlock();
    }   

    public void enterFemale(){
        look.lock();
        try{
            if(free_resources != 0 && menCounterUsing !=0 ){
                womenCounterWaiting ++;
                womenWaitingQueue.await();
            }
                free_resources --;
                womenCounterWaiting--;
                timesFemalesEntered ++;
                womenCounterUsing++;
            
        
        }catch(InterruptedException e){
            look.unlock();
        }

        look.unlock();
   
    }
    

    public void leaveFemale(){

        look.lock();
        womenCounterUsing--;
        free_resources ++;
        if(menCounterWaiting != 0){
            menWaitingQueue.signal();
        }else{
            womenWaitingQueue.signal();
        }
        look.unlock();
    }

    public long getTimesMalesEntered() {
        return timesMalesEntered;
    }

    public long getTimesFemalesEntered() {
        return timesFemalesEntered;
    }




}