
import java.util.*;
import java.io.*;
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private static final String MANUFACTURER_STRING = "M";
  private List productsSupplied = new LinkedList();
  private List assignedProducts = new LinkedList();
  private List manufacturerOrders = new LinkedList();
  public  Manufacturer (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = MANUFACTURER_STRING + (ManufacturerIdServer.instance()).getId();
  }

  public String getName() {
    return name;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public String getAddress() {
    return address;
  }
  
  public Iterator getProvidedProducts(){
    return productsSupplied.iterator();
  }
  
  public String getId() {
    return id;
  }
  public Iterator getListOfOrders(){
     return manufacturerOrders.iterator();
  }
  
  public void assignProduct(SuppliedProduct product){
    productsSupplied.add(product);
  }
  
  public void unassignProduct(SuppliedProduct product){
    productsSupplied.remove(product);
  }
  
  public SuppliedProduct search(String id) {
    Iterator allSupply = getProvidedProducts();
    while(allSupply.hasNext()){
        SuppliedProduct spl_prdct = (SuppliedProduct)(allSupply.next());
        
        if(spl_prdct.getProduct().equals(id)){
            return spl_prdct;
        }
    }
    
    return null;
  }
  
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Manufacturer name " + name + " address " + address + " id " + id + "phone " + phone;
    return string;
  }
}
