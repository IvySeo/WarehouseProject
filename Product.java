// author: Ivy Seo 
// stage 1

import java.util.*;
import java.lang.*;
import java.io.*;
public class Book implements Serializable {
  private static final long serialVersionUID = 1L;

  private int productID; //product id is an integer


  public Product(int productID) {
    this.productID = productID;
  }

// invokes funcion getProductID()
  public int getProductID() { 
    return productID;
  }

// display product ID
  public String toString() {
      return ("Product ID " + productID);
  }
}
