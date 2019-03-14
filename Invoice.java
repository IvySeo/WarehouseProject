// author: Ivy
// state: final

// imports
import java.util.*;
import java.text.*;
import java.io.*;

public class Invoice implements Serializable{

	private static final long serialVersionUID = 1L;

	private Order order;
	private float balance; 

    public Invoice (Order order, float balance){
        this.order = order;
        this.balance = balance;
    }

// get and set - basic functions

    public Order getOrder(){
        return order;
    }

    public float UpdateBalance(float balance){
        this.balance += balance;
        return balance;
    }

    public float getBalance(){
        return balance;
    }
// display the information
  public String toString() {
    String string = " order: " + order.getId() + " balance: " + balance;
    return string;
  }

}