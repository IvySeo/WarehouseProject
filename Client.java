// author: Ivy
// state: final


// imports
import java.io.Serializable;
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String CLIENT_STRING = "C";

  private String name;
  private String address;
  private String phone;
  private List orders = new LinkedList();
  private String id;

  public Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = CLIENT_STRING + (ClientIdServer.instance()).getId();
  }

// get and set - basic functions
  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }


// boolean - if id is equal or not
  public boolean equals(String id) {
    return this.id.equals(id);
  }

// display the information
  public String toString() {
    String string = "Client name: " + name + " address: " + address + " id: " + id + " phone: " + phone;
    return string;
  }

// add client to Client list
private void insertClient() {
    Client clients = ClientList.insertClient();
        if (client == null) {
            System.out.println("Cannot add a client.");
        }
        else {
            System.out.println("Client " + client + " was added successfully.");
        }
    }

  // when warehouse invokes client add order function, it returns client id to Order class
public boolean addOrder(Order o) {
  order.add(o);
  return true;
}

// get a list of waitlisted orders for a client
public Integer getWaitList(Integer id){
        if (ClientList client.contains(id) == null) { // if the id is not found in clientList
            System.out.println("ID does not exist");
        }
        else {
            System.out.println("ID " + id + " exist");
        }
   }

// list client from client list
private void listClients() {
    Iterator clients = ClientList.getClient();

        while (ClientList.hasNext()) {
            System.out.println(ClientList.next());
        }
    }


// list client with Outstanding balance
private void listClientsWithBalance() {
    Iterator clients = ClientList.getClient();
	Client client = new Client();
    	while(clients.hasNext()) {
    		client = (Client) clients.next();
    		if(Invoice.getBalance() > 0) {
    			System.out.println("Client name: " + client.getName() 
    					+ " has a balance of: " 
    					+ Invoice.getBalance());
    		}
            else{
                System.out.println("Has no balance information");
            }
    	}
    }
    

}