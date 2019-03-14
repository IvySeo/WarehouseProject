// author: Ivy Seo 
// stage 1

import java.util.*;
import java.lang.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private int quantity;
  private float salesPrice;
  private String id;
  
  private List suppliers = new LinkedList();
  private List orderedProducts = new LinkedList();
  private static final String PRODUCT_STRING = "P";

  //Constructor
  public Product(String name, int quantity, float salesPrice) {
    this.name = name;
    this.quantity = quantity;
    this.salesPrice = salesPrice;
    id = PRODUCT_STRING + (ProductIdServer.instance()).getId();
  }

// invokes function getProductID()
  public String getId() { 
    return id;
  }
  
  public String getName() {
    return name; 
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public float getSalesPrice() {
    return salesPrice;
  }
  
  
  public void setName(String newName) {
    name = newName;
  }
  
  public void setQuantity(int newQuantity) {
    quantity = newQuantity;
  }
  
  public void setSalesPrice(float newSalesPrice) {
    salesPrice = newSalesPrice;
  }
  
  public void assignSuppliedProduct(SuppliedProduct supply) {
    suppliers.add(supply);
  }
  
  public void unassignSuppliedProduct(SuppliedProduct supply) {
    suppliers.remove(supply);
  }
  
  
  public Iterator getProviders(){
   return suppliers.iterator();
  }
  
  public boolean equals(String id) {
    return this.id.equals(id);
  }

  public Iterator getOrderedProducts()
  {
	  return orderedProducts.iterator();
  }

  public String toString() {
    String string = "Product ID " + id + " name " + name + " quantity " + quantity + " salesPrice " + salesPrice;
    return string;  
  }
}
