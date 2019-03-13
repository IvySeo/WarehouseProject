import java.util.*;
import java.io.*;
public class ManufacturerOrder implements Serializable {
  private static final long serialVersionUID = 1L;

  private Manufacturer supplier;
  private String id;
  private static final String MFCTR_ORDER_STRING = "MO";
  private List shippedProducts = new LinkedList();
  public  ManufacturerOrder (Manufacturer supplier) {
    this.supplier = supplier;
    id = MFCTR_ORDER_STRING + (ManufacturerOrderIdServer.instance()).getId();
  }

  public Manufacturer getManufacturer() {
    return supplier;
  }
  
  public void addProductToOrder(Product product, int qty) {
	  ShippedProduct prdct_toAdd = new ShippedProduct(this, product, qty);
	  shippedProducts.add(prdct_toAdd);
  }
  
  public Iterator getProductsInOrder() {
    return shippedProducts.iterator();
  }
	
}
