//Author: Noah Coyle
//Stage 1

import java.util.*;
import java.io.*;
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;
  private String id;
  private Client client;
  private List orderedProducts = new LinkedList();
  private static final String ORDER_STRING = "I";
  private double totalCost;
  
  
  public Order(){
      
    }
    
  public Order(Client c){
    this.client = c;
    id = ORDER_STRING + (OrderIdServer.instance()).getId();
  }
  
  public void addProduct(Product p, int quantity){
    p.setQuantity(quantity);
    orderedProducts.add(p);
  }

  public double getTotalCost() {
    return totalCost;
  }
  
  public Client getOrderingClient() {
    return client;
  }
  
  public Iterator getOrderedProducts(){
	  return orderedProducts.iterator();
  }
    
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  
  public String toString() {
    String string = "Dummy Action";
    return string;
  }
}
