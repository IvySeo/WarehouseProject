// author: Ivy Seo 
// stage 1

import java.util.*;
import java.lang.*;
import java.io.*;
public class Book implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private int quantity;
  private float salesPrice;
  private String productID;

  //Constructor
  public Product(int productID, name, quantity, salesPrice) {
    this.productID = productID;
    this.name = name;
    this.quantity = quantity;
    this.salesPrice = salesPrice;
  }

// invokes funcion getProductID()
  public int getProductID() { 
    return productID;
  }
  
  public String getName() {
    return name; 
  }
  
  public int getQuantity() {
    return quantity
  }

// display product ID
  public String toString() {
      return ("Product ID " + productID);
  }
}
