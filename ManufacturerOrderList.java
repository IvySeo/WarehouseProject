import java.util.*;
import java.io.*;
public class ManufacturerOrderList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List manufacturerOrders = new LinkedList();
  private static ManufacturerOrderList ManufacturerOrderList;
  private ManufacturerOrderList() {
  }
  
  public static ManufacturerOrderList instance() {
    if (ManufacturerOrderList == null) {
      return (ManufacturerOrderList = new ManufacturerOrderList());
    } else {
      return ManufacturerOrderList;
    }
  }

  public boolean insertManufacturerOrder(ManufacturerOrder order) {
	  manufacturerOrders.add(order);
    return true;
  }
  
  public ManufacturerOrder search(String id) {
    Iterator allManufacturerOrders = getManufacturerOrders();
    while(allManufacturerOrders.hasNext()){
        ManufacturerOrder manufacturerOrder = (ManufacturerOrder)(allManufacturerOrders.next());
        
        if(manufacturerOrder.getId().equals(id)){
            return manufacturerOrder;
        }
    }
    
    return null;
  }
  
  public Iterator getManufacturerOrders(){
     return manufacturerOrders.iterator();
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(ManufacturerOrderList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (ManufacturerOrderList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (ManufacturerOrderList == null) {
          ManufacturerOrderList = (ManufacturerOrderList) input.readObject();
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
    return manufacturerOrders.toString();
  }
}
