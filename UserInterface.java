//Author: Vachia Thoj
//Stage 1

import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface{

    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static final int EXIT = 0;

	//***** STAGE 1 OPEARTIONS *****
    private static final int ADD_CLIENT = 1;
    private static final int ADD_MANUFACTURER = 2;
    private static final int ADD_PRODUCTS = 3;
    private static final int ASSIGN_PRODUCT = 4;
    private static final int UNASSIGN_PRODUCT = 5;
    private static final int LIST_CLIENTS = 6;
    private static final int LIST_MANUFACTURERS = 7;
    private static final int LIST_PRODUCTS = 8;
    private static final int LIST_SUPPLIERS_FOR_PRODUCT = 9;
    private static final int LIST_PRODUCTS_FOR_MANUFACTURER = 10;

	//***** STAGE 2 & 3 OPERATIONS *****
	private static final int ADD_AND_PROCESS_ORDER = 11;
	private static final int PLACE_ORDER_WITH_MANUFACTURER = 12;
	private static final int ACCEPT_CLIENT_PAYMENT = 13;
	private static final int RECEIVE_SHIPMENT = 14;
	private static final int LIST_CLIENTS_WITH_OUTSTANDING_BALANCE = 15;
	private static final int LIST_WAITLISTED_ORDERS_FOR_PRODUCT = 16;
	private static final int LIST_WAITLISTED_ORDERS_FOR_CLIENT = 17;
	private static final int LIST_ORDERS_PLACED_WITH_MANUFACTURER = 18;

	private static final int SAVE = 19;
    private static final int RETRIEVE = 20;
    private static final int HELP = 21;

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

    public float getFloat(String prompt)
    {
        do {
            try
            {
                String item = getToken(prompt);
                Float decimal = Float.valueOf(item);
                return decimal.floatValue();
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
                System.out.println();
                int value = Integer.parseInt(getToken("Enter command: " + HELP + " for help"));
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

		// ***** STAGE 1 OPERATIONS *****
		System.out.println(ADD_CLIENT + " to add a client");
        System.out.println(ADD_MANUFACTURER + " to a add manufacturer");
        System.out.println(ADD_PRODUCTS + " to add products");
        System.out.println(ASSIGN_PRODUCT + " to assign manufacturer to a product ");
        System.out.println(UNASSIGN_PRODUCT + " to unassign manufacturer from a product ");
        System.out.println(LIST_CLIENTS + " to print list of clients");
        System.out.println(LIST_MANUFACTURERS + " to print list of manufacturers");
        System.out.println(LIST_PRODUCTS + " to print list of products");
        System.out.println(LIST_SUPPLIERS_FOR_PRODUCT + " to print list of suppliers for a specifc product");
        System.out.println(LIST_PRODUCTS_FOR_MANUFACTURER + " to print list of products for a manufacturer who supply each product");

		// ***** STAGE 2 & 3 OPERATIONS *****
		System.out.println(ADD_AND_PROCESS_ORDER + " to add and process an order");
		System.out.println(PLACE_ORDER_WITH_MANUFACTURER + " to place an order with a manufacturer");
		System.out.println(ACCEPT_CLIENT_PAYMENT + " to accept payment from a client");
		System.out.println(RECEIVE_SHIPMENT + " to receive a shipment");
		System.out.println(LIST_CLIENTS_WITH_OUTSTANDING_BALANCE + " to print list of clients with an oustanding balance");
		System.out.println(LIST_WAITLISTED_ORDERS_FOR_PRODUCT + " to print list of waitlisted orders for a product");
		System.out.println(LIST_WAITLISTED_ORDERS_FOR_CLIENT + " to print list of waitlisted orders for a client");
		System.out.println(LIST_ORDERS_PLACED_WITH_MANUFACTURER + " to print list of orders placed with manufacturer");

		System.out.println(SAVE + " to save data");
        System.out.println(RETRIEVE + " to retrieve");
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
    public void addProducts()
    {
        Product result;

        do{
            String name = getToken("Enter product name");
            int quantity = getNumber("Enter in quantity");
            float price = getFloat("Enter in price");
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
        boolean result;
        String pid = getToken("Enter Product Id");
        String mid = getToken("Enter Manufacturer Id");
        result = warehouse.assignProductToManufacturer(pid, mid);

        if(result == true){
            System.out.println("SUCCESS: Assigned product to manufacturer ");
        }
        else
        {
            System.out.println("FAILED to assign product");
        }
    }

    //unassignProduct
    public void unassignProduct()
    {
        boolean result;
        String pid = getToken("Enter Product Id");
        String mid = getToken("Enter Manufacturer Id");
        result = warehouse.unassignProductToManufacturer(pid, mid);

        if(result == true){
            System.out.println("SUCCESS: unassigned product to manufacturer");
        }
        else
        {
            System.out.println("FAILED to unassign product");
        }
    }

    //listClients
    public void listClients()
    {
        Iterator allClients = warehouse.getClients();
        System.out.println("CLIENT LIST");
        System.out.println("=========================");
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
        System.out.println("MANUFACTURER LIST");
        System.out.println("=========================");
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
        System.out.println("PRODUCT LIST");
        System.out.println("=========================");
        while (allProducts.hasNext())
        {
            Product product = (Product)(allProducts.next());
            System.out.println(product.toString());
        }
    }

    //listSuppliersForProduct
    public void listSuppliersForProduct()
    {
        String id = getToken("Enter Product Id");
        Iterator result = warehouse.getSuppliersForProduct(id);
        System.out.println("Suppliers for Product: " + id);
        System.out.println("=========================");

        if(result == null){
            return;
        }

        while (result.hasNext())
        {
            System.out.println(result.next());
        }
    }

    //listProductsWithManufacturer
    public void listProductsForManufacturer()
    {
        String id = getToken("Enter Manufacturer Id");
        Iterator result = warehouse.getProductsFromManufacturer(id);

        if(result == null){
            return;
        }

        System.out.println("Products supplied by manufacturer: " + id);
        System.out.println("=========================");
        while (result.hasNext())
        {
            Product product = (Product)(result.next());
            System.out.println("ID: " + product.getId() + " | Name: " + product.getName());
        }
    }

	//Adds and process an order by a client
	public void addProcessOrder()
	{
		String cid = getToken("Enter Client Id");

		if(warehouse.searchClient(id) == true)
        {
            listProducts();
            String pid = getToken("Enter Product Id")

            if(warehouse.searchProduct(id) == true)
            {
                int quantity = getNumber("Enter in quantity")
                warehouse.addAnddProcessOrder(cid, pid, quantity);
            }
        }
	}

	//Place an order with a manufacturer
	public void placeOrderWithManufacturer()
	{
		String id = getToken("Enter Manufacturer Id");
	}

	//Accepts payment from a client
	public void acceptPayment()
	{
		String id = getToken("Enter Client ID: ");
		boolean flag = searchClient(id);

		if(flag == true)
        {



        }
		float payment = getFloat("Enter payment amount:" );
		boolean result;

	}

	//Receive a shipment
	public void receiveShipment()
	{
        System.out.println("Dummy Function");
	}

	//Prints a list of clients with an outstanding balance
	public void listClientsWithOutstandingBalance()
	{
        System.out.println("Dummy Function");
	}

	//Prints a list of waitlisted orders for a product
	public void listWaitlistedOrdersForProduct()
	{
		String id = getToken("Enter Product Id:");
	}

	//Prints a list of waitlisted orders for a client
	public void listWaitlitedOrdersForClient()
	{
		String id = getToken("Enter Client Id:");
	}

	//Prints a list of orders placed with a manufacturer
	public void listOrdersPlacedWithManufacturer()
	{
        String id = getToken("Enter Manufacturer Id");
	}

    //save
    private void save()
    {
        if (warehouse.save())
        {
            System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
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
            Warehouse tempWarehouse = Warehouse.retrieve();
            if (tempWarehouse != null)
            {
                System.out.println(" The Warehouse has been successfully retrieved from the file WarehouseData \n" );
                warehouse = tempWarehouse;
            }
            else
            {
                System.out.println("File doesnt exist; creating new library" );
                warehouse = Warehouse.instance();
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
                case ADD_CLIENT:                            addClient();
                                                            break;
                case ADD_MANUFACTURER:                      addManufacturer();
                                                            break;
                case ADD_PRODUCTS:                          addProducts();
                                                            break;
                case ASSIGN_PRODUCT:                        assignProduct();
                                                            break;
                case UNASSIGN_PRODUCT:                      unassignProduct();
                                                            break;
                case LIST_CLIENTS:                          listClients();
                                                            break;
                case LIST_MANUFACTURERS:                    listManufacturers();
                                                            break;
                case LIST_PRODUCTS:                         listProducts();
                                                            break;
                case LIST_SUPPLIERS_FOR_PRODUCT:            listSuppliersForProduct();
                                                            break;
                case LIST_PRODUCTS_FOR_MANUFACTURER:        listProductsForManufacturer();
                                                            break;
                case ADD_AND_PROCESS_ORDER                  addProcessOrder();
                                                            break;
                case PLACE_ORDER_WITH_MANUFACTURER          placeOrderWithManufacturer();
                                                            break;
                case ACCEPT_CLIENT_PAYMENT                  acceptPayment();
                                                            break;
                case RECEIVE_SHIPMENT                       receiveShipment();
                                                            break;
                case LIST_CLIENTS_WITH_OUTSTANDING_BALANCE  listClientsWithOutstandingBalance();
                                                            break;
                case LIST_WAITLISTED_ORDERS_FOR_PRODUCT     listWaitlistedOrdersForProduct();
                                                            break;
                case LIST_WAITLISTED_ORDERS_FOR_CLIENT      listWaitlitedOrdersForClient();
                                                            break;
                case LIST_ORDERS_PLACED_WITH_MANUFACTURER   listOrdersPlacedWithManufacturer();
                                                            break;
                case SAVE:                                  save();
                                                            break;
                case RETRIEVE:                              retrieve();
                                                            break;
                case HELP:                                  help();
                                                            break;
            }
        }
    }

    public static void main(String[] s)
    {
        UserInterface.instance().process();
    }
}
