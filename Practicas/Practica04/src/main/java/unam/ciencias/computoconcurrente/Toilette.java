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


    public void enterMale() throws InterruptedException {
        look.lock();
        
       

        if(free_resources > 0 && womenCounterUsing > 0){
            menCounterWaiting ++;
            menWaitingQueue.await();
        }   
            free_resources --;
            menCounterWaiting --;
            timesMalesEntered ++;
            menCounterUsing ++;
            System.out.println("Hombre entrando " + menCounterUsing);
        

        look.unlock();
    }


    

    public void leaveMale(){
        look.lock();
        if(menCounterUsing >0){
        free_resources ++;
        menCounterUsing--;
        System.out.println("Hombre Saliendo " + menCounterUsing);
        if(womenCounterWaiting != 0){
            womenWaitingQueue.signal();
        }else{
            menWaitingQueue.signal();
        }
    }
        look.unlock();
    }   

    public void enterFemale() throws InterruptedException {
     
        look.lock();
      
            if(free_resources != 0 && menCounterUsing !=0 ){
                womenCounterWaiting ++;
                womenWaitingQueue.await();
            }
                free_resources --;
                womenCounterWaiting--;
                timesFemalesEntered ++;
                womenCounterUsing++;
                System.out.println("Mujer entrando " + womenCounterUsing);
        

        look.unlock();
   
    }
    

    public void leaveFemale(){

        look.lock();
        if(womenCounterUsing >0){
        womenCounterUsing--;
        System.out.println("Mujer Saliendo " + womenCounterUsing);
        free_resources ++;
        if(menCounterWaiting != 0){
            menWaitingQueue.signal();
        }else{
            womenWaitingQueue.signal();
        }
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