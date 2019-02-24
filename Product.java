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


  public Product(int productID) {
    this.productID = productID;
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
