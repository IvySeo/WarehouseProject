import java.util.*;
import java.text.*;
import java.io.*;

public class ShippedProduct implements Serializable{
    private static final long serialVersionUID = 1L;
    private ManufacturerOrder manufacturerOrder;
    private Product product;
    private int quantity;
    
    public ShippedProduct(ManufacturerOrder manufacturerOrder, Product product, int quantity) {
	       this.manufacturerOrder = manufacturerOrder;
	       this.product = product;
	       this.quantity = quantity;
    }
   
    public int getQuantity() {
    	return quantity;
    }
    
    public ManufacturerOrder getmanufacturerOrder() {
    	return manufacturerOrder;
    }
   
    public Product getProduct() {
    	return product;
    }
}