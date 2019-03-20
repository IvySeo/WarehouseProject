import java.util.*;
import java.io.*;
public class OrderList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List Orders = new LinkedList();
  private static OrderList OrderList;
  private OrderList() {
  }
  
  public static OrderList instance() {
    if (OrderList == null) {
      return (OrderList = new OrderList());
    } else {
      return OrderList;
    }
  }

  public boolean insertOrder(Order order) {
	  Orders.add(order);
    return true;
  }
  
  public Order search(String id) {
    Iterator allOrders = getOrders();
    while(allOrders.hasNext()){
        Order Order = (Order)(allOrders.next());
        
        if(Order.getId().equals(id)){
            return Order;
        }
    }
    
    return null;
  }
  
  public Iterator getOrders(){
     return Orders.iterator();
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(OrderList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (OrderList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (OrderList == null) {
          OrderList = (OrderList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return Orders.toString();
  }
}
