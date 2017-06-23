package barbeirodorminhoco;

import barbeirodorminhoco.Barber;
import barbeirodorminhoco.Customer;
import java.util.concurrent.*;

public class SleepingBarber extends Thread {

  /* PREREQUISITES */


  /* we create the semaphores. First there are no customers and 
   the barber is asleep so we call the constructor with parameter
   0 thus creating semaphores with zero initial permits. 
   Semaphore(1) constructs a binary semaphore, as desired. */
  
    public static Semaphore customers = new Semaphore(0);
    public static Semaphore barber = new Semaphore(0); //Quantidade de barbeiro
    public static Semaphore accessSeats = new Semaphore(1);

  /* we denote that the number of chairs in this barbershop is 5. */

    public static final int CHAIRS = 5; //Cadeiras

  /* we create the integer numberOfFreeSeats so that the customers
   can either sit on a free seat or leave the barbershop if there
   are no seats available */

   public static int numberOfFreeSeats = CHAIRS;

  
     
  
  /* main method */

  public static void main(String args[]) {
    
    SleepingBarber barberShop = new SleepingBarber();  //Cria a barbearia
    barberShop.start();  //Começo da simulação
  }

  public void run(){  

   /* This method will create new customers for a while */
    Barber barbeiro = new Barber();  // Instancia o barbeiro
   barbeiro.start();  //Iniciar o trampo
   for (int i=1; i<16; i++) { //Quantidade de clientes
     Customer aCustomer = new Customer(i);
     aCustomer.start();
     try {
       sleep(2000); //tempo de soneca
     } catch(InterruptedException ex) {};
   }
   
   

  } 
}