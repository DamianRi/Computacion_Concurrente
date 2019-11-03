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



   volatile int menCounterUsing = 0;
   volatile int womenCounterUsing = 0;
   volatile int menCounterWaiting = 0;
   volatile int womenCounterWaiting = 0;


    public Toilette() {
        this.timesMalesEntered = 0;
        this.timesFemalesEntered = 0;
    }


    public void enterMale(){
        try{
        look.lock();

        while(womenCounterUsing > 0){
            System.out.println("Hombre Esperando");
            System.out.println(womenCounterUsing + " Mujeres usando baño");
            womenWaitingQueue.signalAll();
            menWaitingQueue.signalAll();
            menWaitingQueue.await();
        }
        if(womenCounterUsing == 0){
            System.out.println(womenCounterUsing + "Mujeres que estan en el baño");
            System.out.println("Entra Hombre");
            menCounterUsing ++;
            look.unlock();
        }
        
        }catch(InterruptedException e){
            look.unlock();
        }
    }


    

    public void leaveMale(){
        look.lock();
        
        menCounterUsing--;
        if(menCounterUsing == 0){
            womenWaitingQueue.signalAll();
        }
        look.unlock();
    }   

    public void enterFemale(){

        try{
            look.lock();
            while(menCounterUsing > 0){
                System.out.println("Mujer Esperando");
                menWaitingQueue.signalAll();
                womenWaitingQueue.signalAll();
                womenWaitingQueue.await();
            }
            if(menCounterUsing == 0){

               
                womenCounterUsing ++;
                System.out.println("Total de mujeres usando el baño " + womenCounterUsing);
                look.unlock();

            }

        }catch(InterruptedException e){
            look.unlock();
        }
   
   
    }
    

    public void leaveFemale(){

        look.lock();
        womenCounterUsing --;
        System.out.println("Mujer saliendo " + womenCounterUsing);
        if(womenCounterUsing == 0){
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