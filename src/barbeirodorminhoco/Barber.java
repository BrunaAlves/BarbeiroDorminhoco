/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeirodorminhoco;


import static barbeirodorminhoco.SleepingBarber.accessSeats;
import static barbeirodorminhoco.SleepingBarber.barber;
import static barbeirodorminhoco.SleepingBarber.customers;
import static barbeirodorminhoco.SleepingBarber.numberOfFreeSeats;
import static java.lang.Thread.sleep;
import barbeirodorminhoco.Customer;

/**
 *
 * @author alunoces
 */
public class Barber extends Thread {

      public Barber() {}
    
  public void run() {
    while(true) {  // runs in an infinite loop
      try {
      customers.acquire(); // tries to acquire a customer - if none is available he goes to sleep
      accessSeats.release(); // at this time he has been awaken -> want to modify the number of available seats
      numberOfFreeSeats++; // one chair gets free
      barber.release();  // the barber is ready to cut
      accessSeats.release(); // we don't need the lock on the chairs anymore
      this.cutHair();  //cutting...
      
    } catch (InterruptedException ex) {}
    }
  }

    /* this method will simulate cutting hair */
   
  public void cutHair(){
           
    System.out.println("The barber is cutting hair" );
    try {
      sleep(5000);
    } catch (InterruptedException ex){ }
  }
}
