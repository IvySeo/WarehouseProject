// author: Ivy
// state: final

// imports
import java.util.*;
import java.text.*;
import java.io.*;

public class Invoice implements Serializable{

	private static final long serialVersionUID = 1L;

	private Client client;
	private Order order;
	private float balance; 


    publice Invoice (String client, String order, float balance){
        this.client = client;
        this.order = order;
        this.balance = balance;
    }

// get and set - basic functions
    public String getClient(){
        return client;
    }

    public String getOrder(){
        return order;
    }

    public void UpdateBalance(float balance){
        this.balance += balance;
        return balance;
    }

    public float getBalance(){
        return balance;
    }
// display the information
  public String toString() {
    String string = "client: " + client + " order: " + order + " balance: " + balance;
    return string;
  }

}