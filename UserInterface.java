import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface{
	
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;
	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int ADD_MANUFACTURER = 2;
	private static final int ADD_PRODUCT = 3;
	private static final int ASSIGN_PRODUCT = 4;
	private static final int UNASSIGN_PRODUCT = 5;
	private static final int LIST_CLIENTS = 6;
	private static final int LIST_MANUFACTURERS = 7;
	private static final int LIST_PRODUCTS = 8;
	private static final int LIST_SUPPLIERS_FOR_PRODUCT = 9;
	private static final int LIST_PRODUCTS_WITH_MANUFACTURER = 10;
	private static final int SAVE = 11;
	private static final int RETRIEVE = 12;
	private static final int HELP = 13;
	
	private UserInterface()
	{
		if(yesOrNo("Look for saved data and use it?"))
		{
			retrieve();
		}
		else
		{
			warehouse = Warehouse.instance();
		}
	}

	public static UserInterface instance()
	{
    		if (userInterface == null) 
		{
      			return userInterface = new UserInterface();
    		} 
		else 
		{
      			return userInterface;
    		}
  	}

	//getToken
	public String getToken(String prompt) 
	{
    		do {
      			try
			{
        			System.out.println(prompt);
        			String line = reader.readLine();
        			StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        			
				if (tokenizer.hasMoreTokens()) 
				{
          				return tokenizer.nextToken();
        			}

      			} 
			catch(IOException ioe) 
			{
        			System.exit(0);
      			}

    		} while (true);
  	}

	//yesOrNo
	private boolean yesOrNo(String prompt) 
	{
    		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') 
		{
      			return false;
    		}

    		return true;
  	}

	//getNumber
	public int getNumber(String prompt) 
	{
    		do {
      			try 
			{
        			String item = getToken(prompt);
        			Integer num = Integer.valueOf(item);
        			return num.intValue();
      			} 
			catch (NumberFormatException nfe) 
			{
        			System.out.println("Please input a number ");
      			}
    		} while (true);
  	}

	//getDate
	public Calendar getDate(String prompt) 
	{
    		do {
      			try 
			{
        			Calendar date = new GregorianCalendar();
        			String item = getToken(prompt);
        			DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        			date.setTime(df.parse(item));
        			return date;
      			} 
			catch (Exception fe) 
			{
        			System.out.println("Please input a date as mm/dd/yy");
      			}
    		} while (true);
  	}

	//getCommand
	public int getCommand() 
	{
    		do {
      			try 
			{
        			int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        			if (value >= EXIT && value <= HELP) 
				{
          				return value;
        			}
      			} 
			catch (NumberFormatException nfe) 
			{
        			System.out.println("Enter a number");
      			}
    		} while (true);
  	}

	
	//help
	public void help() 
	{
    		System.out.println("Enter a number between 0 and 13 as explained below:");
    		System.out.println(EXIT + " to Exit\n");
    		System.out.println(ADD_CLIENT + " to add a client");
    		System.out.println(ADD_MANUFACTURER + " to a add manufacturer");
    		System.out.println(ADD_PRODUCTS + " to add products");
    		System.out.println(ASSIGN_PRODUCTS + " to  assign manufacturer to a product ");
    		System.out.println(UNASSIGN_PRODUCTS + " to unassign manufacturer from a product ");
    		System.out.println(LIST_CLIENTS + " to print list of clients");
    		System.out.println(LIST_MANUFACTURER + " to print list of manufacturers");
    		System.out.println(LIST_PRODUCTS + " to print list of products");
    		System.out.println(LIST_SUPPLIERS_FOR_PRODUCT + " to print list of suppliers for a specifc product");
    		System.out.println(LIST_PRODUCTS_WITH_MANUFACTURER + " to print list of products and manufacturers who supply each product");
    		System.out.println(SAVE + " to  save data");
    		System.out.println(RETRIEVE + " to  retrieve");
    		System.out.println(HELP + " for help");
  	}

	//addClient
	public void addClient()
	{

		String name = getToken("Enter client name");
		String address = getToken("Enter address");
		String phone = getToken("Enter phone");
		Client result;
		result = warehouse.addClient(name, address, phone);

		if(result == null)
		{
			System.out.println("Could not add client");
		}

		System.out.println(result);
	}

	//addManufacturer
	public void addManufacturer()
	{
		String name = getToken("Enter manufacturer name");
		String address = getToken("Enter address");
		String phone = getToken("Enter phone");
		Manufacturer result;
		result = warehouse.addManufacturer(name, address, phone);

		if(result == null)
		{
			System.out.println("Could not add manufacturer");
		}

		System.out.println(result);
	}

	//addProduct
	public void addProduct()
	{
		Product result;

		do{
			String name("Enter product name");
			int quantity("Enter in quantity");
			float price("Enter in price");
			result = warehouse.addProduct(name, quantity, price);
			
			if(result != null)
			{
				System.out.println(result);
			}
			else
			{
				System.out.println("Product could not be added");
			}

			if(!yesOrNo("Add more products?"))
			{
				break;
			}
		} while (true);
	}

	//assignProduct
	public void assignProduct()
	{
		Product result
		Manufacturer mResult;
		String pid = getToken("Enter Product Id");
		String mid = getToken("Enter Manufacturer Id");
		result 
	}

	//unassignProduct
	public void unassignProduct()
	{
		System.out.println("Dummy Action");
	}

	//listClients
	public void listClients()
	{
		Iterator allClients = warehouse.getClients();
      		while (allClients.hasNext())
		{
	  		Client client = (Client)(allClients.next());
          		System.out.println(client.toString());
      		}
	}

	
	//listManufacturers
	public void listManufacturers()
	{
		Iterator allManufacturers = warehouse.getManufacturers();
      		while (allManufacturers.hasNext())
		{
	  		Manufacturer manufacturer = (Manufacturer)(allManufacturers.next());
          		System.out.println(manufacturer.toString());
      		}
	}

	//list products
	public void listProducts()
	{
		Iterator allProducts = warehouse.getProducts();
      		while (allClients.hasNext())
		{
	  		Product product = (Product)(allProducts.next());
          		System.out.println(product.toString());
      		}
	}

	//listSuppliersForProduct
	public void listSuppliersForProduct()
	{
		System.out.println("Dummy Action");
	}

	//listProductsWithManufacturer
	public void listProductsWithManufacturer()
	{
		System.out.println("Dummy Action");
	}


	//save
	private void save() 
	{
    		if (library.save()) 
		{
      			System.out.println(" The library has been successfully saved in the file LibraryData \n" );
    		} 
		else 
		{
      			System.out.println(" There has been an error in saving \n" );
    		}
  	}

	//retrieve
	private void retrieve() 
	{
    		try 
		{
      			Library tempLibrary = Library.retrieve();
      			if (tempLibrary != null) 
			{
        			System.out.println(" The library has been successfully retrieved from the file LibraryData \n" );
        			library = tempLibrary;
      			} 
			else 
			{
        			System.out.println("File doesnt exist; creating new library" );
        			library = Library.instance();
      			}
    		} 
		catch(Exception cnfe) 
		{
      			cnfe.printStackTrace();
    		}
  	}

	//process
	public void process() {
    		int command;
    		help();
    		while ((command = getCommand()) != EXIT) 
		{
      			switch (command) 
			{
        			case ADD_CLIENT:        		addClient();
                                					break;
        			case ADD_MANUFACTURER:         		addManufacturer();
                                					break;
        			case ADD_PRODUCTS:       		addProducts();
                                					break;
        			case ASSIGN_PRODUCT:      		assignProduct();
                                					break;
        			case UNASSIGN_PRODUCT:      		unassignProduct();
                                					break;
        			case LIST_CLIENTS:       		listClients();
                                					break;
        			case LIST_MANUFACTURERS:        	listManufacturers();
                                					break;
        			case LIST_PRODUCTS:       		listProducts();
                                					break;
        			case LIST_SUPPLIERS_FOR_PRODUCT:      	listSuppliersForProduct();
                                					break;
        			case LIST_PRODUCTS_WITH_MANUFACTURER:  	listProductsWithManufacturer();
                                					break;
        			case SAVE:              		save();
                                					break;
        			case RETRIEVE:          		retrieve();
                                					break;		
        			case HELP:              		help();
                                					break;
      			}
    		}
  	}
	
	public static void main(String[] s) 
	{
    		UserInterface.instance().process();
  	}
}
