//Author: Vachia Thoj

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
    private static final int LIST_CLIENTS_WITH_OUTSTANDING_BALANCE = 14;
    private static final int LIST_WAITLISTED_ORDERS_FOR_PRODUCT = 15;
    private static final int LIST_WAITLISTED_ORDERS_FOR_CLIENT = 16;
    private static final int LIST_ORDERS_PLACED_WITH_MANUFACTURERS = 17;
    private static final int RECEIVE_SHIPMENT = 18;
    
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

    public double getFloat(String prompt)
    {
        do {
            try
            {
                String item = getToken(prompt);
                Double decimal = Double.valueOf(item);
                return decimal.doubleValue();
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
        System.out.println("Enter a number between 0 and 21 as explained below:");
        System.out.println(EXIT + " to Exit");

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
        System.out.println(LIST_PRODUCTS_FOR_MANUFACTURER + " to print list of products supplied by a specific manufacturer");

        // ***** STAGE 2 & 3 OPERATIONS *****
        System.out.println(ADD_AND_PROCESS_ORDER + " to add and process an order");
        System.out.println(PLACE_ORDER_WITH_MANUFACTURER + " to place an order with a manufacturer");
        System.out.println(ACCEPT_CLIENT_PAYMENT + " to accept payment from a client");
        System.out.println(LIST_CLIENTS_WITH_OUTSTANDING_BALANCE + " to print list of clients with an oustanding balance");
        System.out.println(LIST_WAITLISTED_ORDERS_FOR_PRODUCT + " to print list of waitlisted orders for a product");
        System.out.println(LIST_WAITLISTED_ORDERS_FOR_CLIENT + " to print list of waitlisted orders for a client");
        System.out.println(LIST_ORDERS_PLACED_WITH_MANUFACTURERS + " to print list of orders placed with manufacturer");
        System.out.println(RECEIVE_SHIPMENT + " to receive a shipment");
        
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
            double price = getFloat("Enter in price");
            if(quantity < 0 || price < 0){
                System.out.println("Invalid input for quantity and/or price: TRY AGAIN");
                continue;
            }
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
        System.out.println("Assigning Product To Manufacturer");
        System.out.println("=========================");
        String pid = getToken("Enter Product Id (Example P1):");
        String mid = getToken("Enter Manufacturer Id (Example M1):");
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
        System.out.println("Unassigning Product From Manufacturer");
        System.out.println("=========================");
        String pid = getToken("Enter Product Id (Example. P1):");
        String mid = getToken("Enter Manufacturer Id (Example M1):");
        result = warehouse.unassignProductToManufacturer(pid, mid);

        if(result == true){
            System.out.println("SUCCESS: unassigned product from manufacturer");
        }
        else
        {
            System.out.println("FAILED to unassign product");
        }
    }

    //Prints a list of Clients
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


    //Prints a list of Manufacturers
    public void listManufacturers()
    {
        Iterator allManufacturers = warehouse.getManufacturers();
        System.out.println("MANUFACTURER LIST");
        System.out.println("=========================");
        while(allManufacturers.hasNext())
        {
            Manufacturer manufacturer = (Manufacturer)(allManufacturers.next());
            System.out.println(manufacturer.toString());
        }
    }

    //Prints a list of Products
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
        String id = getToken("Enter Product Id (Example. P1):");
        Iterator result = warehouse.getSuppliersForProduct(id);
        System.out.println("Suppliers for Product: " + id);
        System.out.println("=========================");

        if(result == null){
            System.out.println("No Data: Exiting Action");
            return;
        }

        while (result.hasNext())
        {
            System.out.println(result.next());
        }
    }
    
    //Lists all products supplied by a manufacturer
    public void listProductsForManufacturer()
    {
        String id = getToken("Enter Manufacturer Id (Example. M1):");
        Iterator result = warehouse.getProductsFromManufacturer(id);

        if(result == null){
            System.out.println("No Data: Exiting Action");
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
        System.out.println("Making An Order");
        System.out.println("=========================");
        String cid = getToken("Enter Client Id (Example. C1):");
        
        Client client = warehouse.searchClient(cid);
        if(client != null)
        {
            listProducts();
            String pid = getToken("Enter Product Id or 0 to stop");
            
            while(!(pid.equals("0"))){
                int quantity = getNumber("Enter quantity");
                if(quantity <= 0){
                    System.out.println("Invalid Quantity: Try Again");
                    continue;
                }
                
                boolean result = warehouse.addAndProcessOrder(cid, pid, quantity);
                if(result == false){
                    System.out.println("Invalid Product: " + pid);
                }
                pid = getToken("Enter another Product Id or 0 to stop");
            }
        }
        else
        {
            System.out.println("No Data For: (" + cid + "); EXITING ACTION");
        }
    }

    //Place an order with a manufacturer
    public void placeOrderWithManufacturer()
    {
        System.out.println("Placing An Order With A Manufacturer");
        System.out.println("=========================");
        String mid = getToken("Enter Manufacturer Id (Example. M1):");
        Iterator result = warehouse.getProductsFromManufacturer(mid);

        if(result == null){
            System.out.println("No Data: Exiting Action");
            return;
        }
        
        //Query list of products supplied by a manufacturer
        System.out.println("Products supplied by manufacturer: " + mid);
        System.out.println("=========================");
        while (result.hasNext())
        {
            Product product = (Product)(result.next());
            System.out.println("ID: " + product.getId() + " | Name: " + product.getName());
        }
        
        
        int numProducts = 0;
        //Ask for product that is wanted to be ordered
        String pid = getToken("Enter Product Id or 0 to stop");
        Shipment shipment = new Shipment((warehouse.searchManufacturer(mid)));
        ManufacturerOrder m_order;
        while(!(pid.equals("0"))){
           int quantity = getNumber("Enter in quantity"); 
           
           if(quantity <= 0){
               System.out.println("Invalid Quantity: Try Again");
               continue;
           }
           
           result = warehouse.getProductsFromManufacturer(mid);
           boolean flag = false;
           while (result.hasNext())
           {
               Product product = (Product)(result.next());
               
               //Check if manufacturer supplies product
               if(product.getId().equals(pid)){
                   m_order = new ManufacturerOrder(product, quantity);
                   shipment.addProduct(m_order);
                   ++numProducts;
                   flag = true;
               }
           }
           
           if(flag == false){
               System.out.println("Manufacturer: " + mid + " does not supply product: " + pid);
               System.out.println();
           }
           
           pid = getToken("Enter another Product Id or 0 to stop");
        }
            
        if(numProducts > 0){
          warehouse.placeOrderWithManufacturer(shipment);   
          System.out.println("ORDER PLACED WITH MANUFACTURER");
        }
    }

    //Accepts payment from a client
    public void acceptPayment()
    {
        System.out.println("Make A Payment");
        System.out.println("=========================");
        String id = getToken("Enter Client ID: ");
        Client client = warehouse.searchClient(id);
        if(warehouse.searchClient(id) != null){
            double clientBalance = client.getBalance();
            System.out.println("Balance: $" + clientBalance);
            
            if(clientBalance == 0.0){
                return;
            }
            
            double payment = getFloat("Enter payment amount:" );
            while(payment < 0.0 || payment > clientBalance){
               System.out.println("Invalid amount");
               payment = getFloat("Enter valid payment amount:");
            }
            
            double newBalance = warehouse.acceptClientPayment(id, payment);
            System.out.println("UPDATED BALANCE: $" + newBalance);
        }
    }

    //Prints a list of clients with an outstanding balance
    public void listClientsWithOutstandingBalance()
    {
        Iterator allClients = warehouse.getClients();
        System.out.println("CLIENTS WITH OUTSTANDING BALANCE");
        System.out.println("=========================");
        while (allClients.hasNext())
        {
            Client client = (Client)(allClients.next());
            if(client.getBalance() > 0.0){
                System.out.println(client.toString());
            }
        }
    }

    //Prints a list of waitlisted orders for a product
    public void listWaitlistedOrdersForProduct()
    {
        String id = getToken("Enter Product Id:");
        Iterator allOrders = warehouse.getWaitListedOrdersForProduct(id);
        if(allOrders == null){
            return;
        }
        
        System.out.println("Waitlisted Orders for a product: " + id);
        System.out.println("=========================");
        while(allOrders.hasNext()){
            Order order = (Order)(allOrders.next());
            if(order.getProduct().getId().equals(id)){
                System.out.println(order.toString());
            }
        }
    }

    //Prints a list of waitlisted orders for a client
    public void listWaitlitedOrdersForClient()
    {
        String id = getToken("Enter Client Id:");
        Iterator allOrders = warehouse.getWaitListedOrdersForClient(id);
        
        if(allOrders == null){
            return;
        }
        
        System.out.println("Waitlisted Orders for Client: " + id);
        System.out.println("=========================");
        while(allOrders.hasNext()){
            Order order = (Order)(allOrders.next());
            if(order.getClient().getId().equals(id)){
                System.out.println(order.toString());
            }
        }
    }
   
    //Prints a list of orders placed with manufacturers
    public void listOrdersPlacedWithManufacturers()
    {
        Iterator result = warehouse.getOrdersPlacedWithManufacturer();
        
        System.out.println("Orders Placed with Manufacturers: ");
        System.out.println("=========================");
        while (result.hasNext()){
            Shipment shipment = (Shipment)(result.next());
            
            Iterator allProducts = shipment.getProducts();
            System.out.println("Shipment ID: " + shipment.getId() + " | Manufacturer ID: " + shipment.getManufacturer().getId());
            
            while(allProducts.hasNext()){
                ManufacturerOrder m_order = (ManufacturerOrder)(allProducts.next());
                System.out.println("   " + m_order.toString());
            }
        }
    }
    
    //Receive a shipment
    public void receiveShipment()
    {
        String id = getToken("Enter Shipment Id: (Example: S1)");
        Iterator result = warehouse.getShipment(id);
        if(result != null){
            //List products in shipment
            System.out.println("Shipment ID: " + id + " | Receiving Follwoing Products");
            while(result.hasNext()){
                ManufacturerOrder m_order = (ManufacturerOrder)(result.next());
                System.out.println("   " + m_order.toString());
            }
         
            //Process the received products
            warehouse.receiveShipment(id);
        }
        else
        {
            System.out.println("NO DATA FOUND FOR: " + id);
        }
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
                System.out.println("File doesnt exist; creating new warehouse" );
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
                case ADD_AND_PROCESS_ORDER:                 addProcessOrder();
                                                            break;
                case PLACE_ORDER_WITH_MANUFACTURER:         placeOrderWithManufacturer();
                                                            break;
                case ACCEPT_CLIENT_PAYMENT:                 acceptPayment();
                                                            break;
                case LIST_CLIENTS_WITH_OUTSTANDING_BALANCE: listClientsWithOutstandingBalance();
                                                            break;
                case LIST_WAITLISTED_ORDERS_FOR_PRODUCT:    listWaitlistedOrdersForProduct();
                                                            break;
                case LIST_WAITLISTED_ORDERS_FOR_CLIENT:     listWaitlitedOrdersForClient();
                                                            break;
                case LIST_ORDERS_PLACED_WITH_MANUFACTURERS: listOrdersPlacedWithManufacturers();
                                                            break;
                case RECEIVE_SHIPMENT:                      receiveShipment();
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
