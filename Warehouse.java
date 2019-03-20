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
  private OrderList orderList;
  private ManufacturerOrderList manufacturerOrderList;

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
  
  public Iterator getWaitList() {
	  return waitList.getOrders();
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


  //Stage 2
  public Order createOrder(String clnt_id)
  {
	 Client clnt = clientList.search(clnt_id);
	 
	 if(clnt != null)
	 {
		 Order ord = new Order(clnt);
		 
		 clnt.addOrder(ord);
		 orderList.insertOrder(ord);
		 return ord;
	 }
	 return null;
  }
  
  public boolean processOrder(Order ord)
  {
		Iterator ord_prd_iterator = ord.getOrderedProducts();
		
		//Check if all orderedproducts are in stock in inventory
		while (ord_prd_iterator.hasNext())
		{
			OrderedProduct ord_prd = (OrderedProduct)ord_prd_iterator.next();

    		Product prd_toCheckQtyFor = ord_prd.getProduct();

			if(prd_toCheckQtyFor.getQuantity() < ord_prd.getQuantity())
			{
				//If any one orderedproduct cant be fulfilled, put order on waitlist immediately.
				waitList.insertOrder(ord);
				
				return false;
			}
		}
		
		//Fulfill Order
		Iterator ord_prd_iterator_fulfill = ord.getOrderedProducts();
		while (ord_prd_iterator.hasNext())
		{
			OrderedProduct ord_prd = (OrderedProduct)ord_prd_iterator.next();

    		Product prd_inInventory = ord_prd.getProduct();

			prd_inInventory.deductQuantity(ord_prd.getQuantity());
			
			//Possibly update order object?
		}
		
		
		return true;
  }
  
    public void placeOrderWithManufacturer(String mfct_ID, String prd_ID, int qty)
    {
        Manufacturer mfct = manufacturerList.search(mfct_ID);
        Product prdct = productList.search(prd_ID);
        
        if(mfct != null && prdct != null){
        	ManufacturerOrder mfct_ord = new ManufacturerOrder(mfct);
        	
        	mfct_ord.addProductToOrder(prdct, qty);
        	
        	mfct.addOrder(mfct_ord);
        	
        	manufacturerOrderList.insertManufacturerOrder(mfct_ord);
        }
    }

    public boolean acceptClientPayment(String clnt_ID, String ord_ID, float payment)
    {
        Client clnt = clientList.search(clnt_ID);
        
        if(clnt != null) {
        	
        	Order ord = clnt.search(ord_ID);
        	
        	if (ord != null)
        	{
        		Invoice inv = ord.getInvoice();
        		
        		if (inv != null)
        		{
        			inv.UpdateBalance(payment);
        			
        			return true;
        		}
        	}
        }
        return false;
    }

    //Stage 3
    public boolean canFulfill_OrderedProduct(OrderedProduct ord_prd, ManufacturerOrder mOrder)
    {
    	//Check manufacturerOrder OR product list for enough quantity to fulfill orderedproduct in order.
    	ShippedProduct shp_prd = mOrder.getProductsInOrder();
    	if(shp_prd != null)
    	{
    		Product prd_inMOrder = shp_prd.getProduct();

    		Product prd_toCheckQtyFor = ord_prd.getProduct();
    		
    		if(prd_inMOrder == prd_toCheckQtyFor)
    		{
    			if(shp_prd.getQuantity() >= ord_prd.getQuantity())
    			{
    				return true;
    			}
    		}
    		else
    		{
    			if(prd_toCheckQtyFor.getQuantity() >= ord_prd.getQuantity())
    			{
    				return true;
    			}
    		}
    		
    	}
    	return false;
    	
    }
    
    //ensure EVERY orderedproduct CAN BE FULFILLED before calling. See above function.
    public Order fulfillOrder(Order ord, ManufacturerOrder mfct_ord)
    {
    	//Remove order from waitlist
    	waitList.removeOrder(ord);
    	
    	//deduct quantity of each orderedproduct from Morder or Product List
    	Iterator ord_prd_iterator = ord.getOrderedProducts();
    	
    	while (ord_prd_iterator.hasNext())
    	{
    		OrderedProduct ord_prd = (OrderedProduct)ord_prd_iterator.next();
    		
	    	ShippedProduct shp_prd = mfct_ord.getProductsInOrder();
	    	if(shp_prd != null)
	    	{
	    		Product prd_inMOrder = shp_prd.getProduct();
	
	    		Product prd_toCheckQtyFor = ord_prd.getProduct();
	    		
	    		if(prd_inMOrder == prd_toCheckQtyFor)
	    		{
	    			if(shp_prd.getQuantity() >= ord_prd.getQuantity())
	    			{
	    				shp_prd.deductQuantity(ord_prd.getQuantity());
	    			}
	    		}
	    		else
	    		{
	    			if(prd_toCheckQtyFor.getQuantity() >= ord_prd.getQuantity())
	    			{
	    				prd_toCheckQtyFor.deductQuantity(ord_prd.getQuantity());
	    			}
	    		}  		
	    	}
    	}
    	return ord;
    }
    
    public boolean addRemaining_ShippedProduct(ManufacturerOrder mfct_ord)
    {
    	//Add Remaining shipped product (if any) to product in product List
    	ShippedProduct shp_prd = mfct_ord.getProductsInOrder();
    	
    	if(shp_prd != null)
    	{
    		if (shp_prd.getQuantity() > 0)
    		{
    			shp_prd.getProduct().addQuantity(shp_prd.getQuantity());
    			return true;
    		}
    	}
    	return false;
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
