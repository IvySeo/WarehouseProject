import java.util.*;
import java.io.*;
public class Order implements Serializable {
  private String date;
  private Double totalCost;
  private Client orderingClient;
  private static final String ORDER_STRING = "O";
  private List productsInOrder = new LinkedList();
  public  Order (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
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
	
  public void setDate (String newDate) {
    date = newDate;
  }

  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Member name " + name + " address " + address + " id " + id + "phone " + phone;
    return string;
  }
}
