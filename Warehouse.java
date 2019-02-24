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
	clientListList = clientListList.instance();
  }
  public static Warehouse instance() {
    if (Warehouse == null) {
      ProductIdServer.instance(); // instantiate all singletons
	  ClientIdServer.instance(); 
	  ManufacturerIdServer.instance(); 
      return (Warehouse = new Warehouse());
    } else {
      return Warehouse;
    }
  }
  public Manufacturer addManufacturer(String Name, String Address, String PhoneNumber) {
    Manufacturer manufacturer = new Manufacturer(Name, Address, PhoneNumber);
    if (manufacturerList.insertManufacturer(manufacturer)) {
      return (Manufacturer);
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
  public Product addProduct(String name, int quantity, Double price) {
    Product product = new Product(name, quantity, price);
    if (productList.insertProduct(product)) {
      return (product);
    }
    return null;
  }
  
  public void assignProductToManufacturer (String ProductID, String ManufacturerID)
  {
	(manufacturerList.search(ManufacturerID)).assignProduct(productList.search(ProductID));
	return;
  }
	
  public void unassignProductFromManufacturer (String ProductID, Manufacturer ManufacturerID)
  {
	(manufacturerList.search(ManufacturerID)).unassignProduct(productList.search(ProductID));
	return;
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
	  return (productList.search(ProductID)).getProviders();
  }
  
    public Iterator getProductsFromManufacturer(String ManufacturerID){
	  return (manufacturerList.search(ManufacturerID)).getProvidedProducts();
  }
  
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      productList.retrieve(input);
	  manufacturerList.retrieve(input);
	  clientList.retrieve(input);
      return Warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(Warehouse);
      output.writeObject(productList.instance());
	  output.writeObject(manufacturerList.instance());
	  output.writeObject(clientList.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(Warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (Warehouse == null) {
        Warehouse = (Warehouse) input.readObject();
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
