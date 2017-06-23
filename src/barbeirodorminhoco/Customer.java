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

/**
 *
 * @author alunoces
 */
public class Customer extends Thread {
      int iD;
  boolean notCut=true;
  
  /* Constructor for the Customer */
    
  public Customer(int i) {
    iD = i;
  }

  public void run() {   
    while (notCut) {  // as long as the customer is not cut 
      try {
      accessSeats.acquire();  //tries to get access to the chairs
      if (numberOfFreeSeats > 0) {  //if there are any free seats
        System.out.println("Customer " + this.iD + " just sat down.");
        numberOfFreeSeats--;  //sitting down on a chair
        customers.release();  //notify the barber that there is a customer
        accessSeats.release();  // don't need to lock the chairs anymore  
        try {
	barber.acquire();  // now it's this customers turn but we have to wait if the barber is busy
        notCut = false;  // this customer will now leave after the procedure
        this.get_haircut();  //cutting...
        } catch (InterruptedException ex) {}
      }   
      else  {  // there are no free seats
        System.out.println("There are no free seats. Customer " + this.iD + " has left the barbershop.");
        accessSeats.release();  //release the lock on the seats
        notCut=false; // the customer will leave since there are no spots in the queue left.
      }
     }
      catch (InterruptedException ex) {}
    }
  }

  /* this method will simulate getting a hair-cut */
  
  public void get_haircut(){
    System.out.println("Customer " + this.iD + " is getting his hair cut");
    try {
    sleep(5050);
    } catch (InterruptedException ex) {}
  }
    
}
