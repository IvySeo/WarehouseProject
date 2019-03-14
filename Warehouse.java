//Author: Noah Coyle
//Stage 1

import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;

  private ProductList productList;
  private ManufacturerList manufacturerList;
  private ClientList clientList;
  private WaitList waitList;

  private static Warehouse warehouse;

  private Warehouse() {
    productList = productList.instance();
    manufacturerList = manufacturerList.instance();
    clientList = clientList.instance();
    waitList = waitList.instance();
  }

  public static Warehouse instance() {
    if (warehouse == null) {
      ProductIdServer.instance(); // instantiate all singletons
      ClientIdServer.instance();
      ManufacturerIdServer.instance();
      OrderIdServer.instance();
      ManufacturerOrderIdServer.instance();
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

  //Assigning and Un-assigning Product Functions
  public boolean assignProductToManufacturer (String productID, String manufacturerID, float price)
  {
	Manufacturer mfct = manufacturerList.search(manufacturerID);
	Product prdct = productList.search(productID);
	
    if(mfct != null && prdct != null){
    	SuppliedProduct spl_prdct = new SuppliedProduct(mfct, prdct, price);
    	
        mfct.assignProduct(spl_prdct);
        prdct.assignSuppliedProduct(spl_prdct);
        return true;
    }
    return false;
  }

  public boolean unassignProductToManufacturer (String productID, String manufacturerID)
  {
	Manufacturer mfct = manufacturerList.search(manufacturerID);
	Product prdct = productList.search(productID);
	
	  
    if(mfct != null && prdct != null){
    	SuppliedProduct spl_prdct = mfct.search(productID);
    	
    	if(spl_prdct != null)
    	{
    		mfct.unassignProduct(spl_prdct);
        	prdct.unassignSuppliedProduct(spl_prdct);
        	
        	spl_prdct = null;
        	
        	return true;
    	}
    }
    return false;
  }

  //List Getters
  public Iterator getClients() {
      return clientList.getClients();
  }

  public Iterator getManufacturers() {
      return manufacturerList.getManufacturers();
  }

  public Iterator<Product> getProducts() {
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
  
  public Iterator getWaitList() {
	  return waitList.getOrders();
  }

  //Stage 2
  public boolean addAndProcessOrder(String id, Order order){
      if((clientList.search(id)).addOrder(order) != false ){
          return true;
      }
      return false;
  }

    public void placeOrderWithManufacturer(String mfct_ID, String prd_ID, int qty)
    {
        Manufacturer mfct = manufacturerList.search(mfct_ID);
    }

    public void acceptClientPayment(String clnt_ID, String ord_ID, double payment)
    {
        
    }

    //Stage 3
    public void recieveShipment()
    {


    }
    
    //Search Functions
    public Client searchClient(String id)
    {
        if(clientList.search(id) != null)
        {
            return (clientList.search(id));
        }

        return null;
    }

    public Product searchProduct(String id)
    {
        if(productList.search(id) != null)
        {
            return (productList.search(id));
        }

        return null;
    }
 
   //Save and Load Functions
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ProductIdServer.retrieve(input);
      ManufacturerIdServer.retrieve(input);
      ClientIdServer.retrieve(input);
      OrderIdServer.retrieve(input);
      ManufacturerOrderIdServer.retrieve(input);
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
      output.writeObject(OrderIdServer.instance());
      output.writeObject(ManufacturerOrderIdServer.instance());
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
