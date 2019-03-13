import java.util.*;
import java.text.*;
import java.io.*;

public class OrderedProduct implements Serializable{
    private static final long serialVersionUID = 1L;
    private Order order;
    private Product product;
    private int quantity;
    
    public OrderedProduct(Order order, Product product, int quantity) {
	       this.order = order;
	       this.product = product;
	       this.quantity = quantity;
    }
   
    public int getQuantity() {
    	return quantity;
    }
    
    public Order getOrder() {
    	return order;
    }
   
    public Product getProduct() {
    	return product;
    }
}
