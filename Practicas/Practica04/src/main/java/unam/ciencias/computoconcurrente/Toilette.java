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



    int maleCounterUsing = 0;
    int femaleCounterUsing = 0;
    int maleCounterWaiting = 0;
    int femaleCounterWaiting = 0;


    public Toilette() {
        this.timesMalesEntered = 0;
        this.timesFemalesEntered = 0;
    }


    public void enterMale(){
        /*
        useTM.lock();
        useT.lock();
        maleCounterUsing.getAndIncrement();
        this.timesMalesEntered ++;
    */
        try{
            look.lock();
            while(femaleCounterUsing != 0){
                maleCounterWaiting++;
                menWaitingQueue.await();
            }
            if (maleCounterWaiting < 0) {
                maleCounterWaiting = 0;
            }else{
                maleCounterWaiting--;
            }
            maleCounterUsing++;
            this.timesMalesEntered++;

            look.unlock();

        }catch(InterruptedException e){
            look.unlock();
        }
    }


    

    public void leaveMale(){

        /*
        maleCounterUsing.getAndDecrement();
        if(maleCounterUsing.get() == 0){
            useT.unlock();
        }else{
            useTM.unlock();
        }
        */
        look.lock();
        maleCounterUsing--;
        if (femaleCounterWaiting != 0) {
            womenWaitingQueue.signalAll();
        }

        look.unlock();


    }   

    public void enterFemale(){

        /*
        useTF.lock();
        useT.lock();
        femaleCounterUsing.getAndIncrement();
        this.timesFemalesEntered ++;
        **/
        try{
            look.lock();
            while(maleCounterUsing != 0){
                femaleCounterWaiting++;
                womenWaitingQueue.await();
            }
            if (femaleCounterWaiting < 0) {
                femaleCounterWaiting = 0;
            }else{
                femaleCounterWaiting--;
            }
            femaleCounterUsing++;
            this.timesFemalesEntered++;

            look.unlock();

        }catch(InterruptedException e){
            look.unlock();
        }
   
   
    }
    

    public void leaveFemale(){
        /*
        femaleCounterUsing.getAndDecrement();
        if(femaleCounterUsing.get() == 0){
            useT.unlock();
        }else{
            useTF.unlock();
        }
        **/
        look.lock();
        femaleCounterUsing--;
        if (maleCounterWaiting != 0) {
            menWaitingQueue.signalAll();
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