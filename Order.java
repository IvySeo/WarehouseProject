
import java.util.*;
import java.io.*;
public class Order implements Serializable {
  private String date;
  private Double totalCost;
  private Client orderingClient;
  private String id;
  private static final String ORDER_STRING = "O";
  private List productsInOrder = new LinkedList();
  private List manufacturersForOrder = new LinkedList();
  public  Order (String date, Double totalCost, Client c) {
    this.date = date;
    this.totalCost = totalCost;
    this.orderingClient = c;
    id = ORDER_STRING + (OrderIdServer.instance()).getId();
  }

  public String getDate() {
    return date;
  }

  public Double getTotalCost() {
    return totalCost;
  }
  
  public Client getOrderingClient() {
    return orderingClient;
  }
  
  public List getProductsInOrder() {
    return productsInOrder;
  }

  public List getManufacturersForOrder() { return manufacturersForOrder; }
	
  public void setDate (String newDate) {
    date = newDate;
  }

  public boolean equals(String id) {
    return this.id.equals(id);
  }
  
  public String toString() {
    String string = "Dummy Action";
    return string;
  }
}
