//Author: Noah Coyle
//Stage 1

import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private ProductList productList;
  private ManufacturerList manufacturerList;
  private ClientList clientList;
  
  private static Warehouse warehouse;
  
  private Warehouse() {
    productList = productList.instance();
    manufacturerList = manufacturerList.instance();
	clientList = clientList.instance();
  }
	
  public static Warehouse instance() {
    if (warehouse == null) {
      ProductIdServer.instance(); // instantiate all singletons
	  ClientIdServer.instance(); 
	  ManufacturerIdServer.instance(); 
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
	
  public Manufacturer addManufacturer(String Name, String Address, String PhoneNumber) {
    Manufacturer manufacturer = new Manufacturer(Name, Address, PhoneNumber);
    if (manufacturerList.insertManufacturer(manufacturer)) {
      return (manufacturer);
    }
    return null;
  }
	
  public Client addClient(String name, String address, String phone) {
    Client client = new Client(name, address, phone);
    if (clientList.insertClient(client)) {
      return (client);
    }
    return null;
  }
	
  public Product addProduct(String name, int quantity, float price) {
    Product product = new Product(name, quantity, price);
    if (productList.insertProduct(product)) {
      return (product);
    }
    return null;
  }
  
  public boolean assignProductToManufacturer (String productID, String manufacturerID)
  {
	if(manufacturerList.search(manufacturerID) != null && productList.search(productID) != null){
		
		(manufacturerList.search(manufacturerID)).assignProduct(productList.search(productID));
		(productList.search(productID)).assignManufacturer(manufacturerList.search(manufacturerID));
		return true;
	}
	return false;
  }
	
  public boolean unassignProductToManufacturer (String productID, String manufacturerID)
  {
	if(manufacturerList.search(manufacturerID) != null && productList.search(productID) != null){
		
		(manufacturerList.search(manufacturerID)).unassignProduct(productList.search(productID));
		(productList.search(productID)).assignManufacturer(manufacturerList.search(manufacturerID));
		return true;
	}
	return false;
  }

  public Iterator getClients() {
      return clientList.getClients();
  }

  public Iterator getManufacturers() {
      return manufacturerList.getManufacturers();
  }
  
  public Iterator getProducts() {
      return productList.getProducts();
  }
  
  public Iterator getSuppliersForProduct(String ProductID){
	  
	  if(productList.search(ProductID) != null){
		  return (productList.search(ProductID)).getProviders();
	  }
	  
	  return null;
	  
  }
  
    public Iterator getProductsFromManufacturer(String ManufacturerID){
	    
	  if(manufacturerList.search(ManufacturerID) != null){
		  return (manufacturerList.search(ManufacturerID)).getProvidedProducts();
	  }
	  
	    return null;
  }
  
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ProductIdServer.retrieve(input);
	  ManufacturerIdServer.retrieve(input);
	  ClientIdServer.retrieve(input);
      return warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
	
  public static boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ProductIdServer.instance());
	  output.writeObject(ManufacturerIdServer.instance());
	  output.writeObject(ClientIdServer.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
	
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
	
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (warehouse == null) {
        warehouse = (Warehouse) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
 
}
