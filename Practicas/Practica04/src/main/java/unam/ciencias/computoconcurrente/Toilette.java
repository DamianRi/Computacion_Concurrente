package unam.ciencias.computoconcurrente;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Toilette {
    private volatile long timesMalesEntered;
    private volatile long timesFemalesEntered;

    private Lock useT = new ReentrantLock();
    
    private Lock useTM = new ReentrantLock();   // Use Toilet Males
    private Lock useTF = new ReentrantLock();   // Use Toilet Females
    
    private Condition waitingTM = useTM.newCondition();    // Usiong Toilet Male
    private Condition waitingTF = useTF.newCondition();    // Using toilet Female
    private Condition waitingT = useT.newCondition();

    AtomicInteger maleCounterUsing = new AtomicInteger(0);
    AtomicInteger femaleCounterUsing = new AtomicInteger(0);
    AtomicInteger maleCounterWaiting = new AtomicInteger(0);
    AtomicInteger femaleCounterWaiting = new AtomicInteger(0);


    public Toilette() {
        this.timesMalesEntered = 0;
        this.timesFemalesEntered = 0;
    }


    public void enterMale(){
        try {
            useTM.lock();
            useT.lock();    // Usamos el toilet
            while(femaleCounterUsing.get() > 0){    // Mientras haya mujeres
                System.out.println("Hombre esperando...");
                useT.unlock();
                waitingTM.wait();
                //waitingT.await();   // Hacemos esperar a los hombres
            }
            maleCounterUsing.incrementAndGet();
            this.timesMalesEntered++;

            if (maleCounterUsing.get() == 1) {   // Un hombre en toilet
                useTF.lock();   // El primer hombre bloquea al uso de las mujeres
                System.out.println("Bloqueando uso de mujeres ... ");
            }
            // hacer algo
        }catch (InterruptedException e){
            System.out.println(e.toString());
            System.out.println(" Liberando todos los candados ");
            // Liberando todos los candados obtenidos
            useTF.unlock();
            useT.unlock();
            useTM.unlock();
        }finally{
            useT.unlock();
            useTM.unlock(); 
        }

    }

    public void leaveMale() {
        try {
            useTM.lock();
            useT.lock();
            maleCounterUsing.incrementAndGet();
            if (maleCounterUsing.get() == 0) {  // El último hombre
                useTF.unlock(); // desbloquea el uso de las mujeres
                waitingTF.signalAll();    // Se avisa a las mujeres esperando         
                System.out.println("... Liberando uso mujeres");
            }
            //waitingT.signalAll();
        }finally{
            useT.unlock();
            useTM.unlock();
        }

    }   

    public void enterFemale(){
        try {
            useTF.lock();
            useT.lock();    // Usamos el toilet
            while(maleCounterUsing.get() > 0){  // Si hay al menos un hombre
                System.out.println("Mujer esperando ...");
                useT.unlock();
                waitingTF.wait();   // Esperamos 
                //waitingT.await();   // Esperamos mientras los usan los hombres
            }
            femaleCounterUsing.incrementAndGet();
            this.timesFemalesEntered++;
            if (femaleCounterUsing.get() == 1) {   // La primera mujer bloquea el uso de los hombres
                useTM.lock();
                System.out.println("Bloqueando uso de hombres ...");
            }
        }catch (InterruptedException e){
            System.out.println(e.toString());
            System.out.println(" Liberando todos los candados ");
            //Liberando todos los candados obtenidos
            useTM.unlock();
            useT.unlock();
            useTF.unlock();             
        }finally{
            useT.unlock();
            useTF.unlock();             
        }

    }

    public void leaveFemale() {
        try{
            useTF.lock();
            useT.lock();
            femaleCounterUsing.decrementAndGet();
            if (femaleCounterUsing.get() == 0) {    // La úlitma mujer
                useTM.unlock(); // Desbloquea el uso de los hombres
                waitingTM.signalAll();  // Se avisa a los hombres esperando
                System.out.println("... Liberando uso hombres");
            }
            //usingTM.signalAll();
        }finally{
            useT.unlock();
            useTF.unlock();        
        }
    }

    public long getTimesMalesEntered() {
        return timesMalesEntered;
    }

    public long getTimesFemalesEntered() {
        return timesFemalesEntered;
    }
}
