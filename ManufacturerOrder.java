import java.util.*;
import java.io.*;
public class ManufacturerOrder implements Serializable {
  private static final long serialVersionUID = 1L;

  private Manufacturer supplier;
  private String id;
  private static final String MFCTR_ORDER_STRING = "MO";
  private ShippedProduct shippedProducts;
  private float totalCost;
  
  public  ManufacturerOrder (Manufacturer supplier) {
    this.supplier = supplier;
    id = MFCTR_ORDER_STRING + (ManufacturerOrderIdServer.instance()).getId();
  }

  public Manufacturer getManufacturer() {
    return supplier;
  }
  
  public void addProductToOrder(Product product, int qty) {
	  ShippedProduct prdct_toAdd = new ShippedProduct(this, product, qty);
	  shippedProducts = prdct_toAdd;
  }
  
  public float getTotalCost()
  {
	  return totalCost;
	  
  }
  
  public float calculateTotalCost(SuppliedProduct spl_prd)
  {
	  if(shippedProducts != null)
	  {
		  totalCost = spl_prd.getSupplyPrice() * shippedProducts.getQuantity();
	  }
	  return totalCost;
  }
  
  public ShippedProduct getProductsInOrder() {
    return shippedProducts;
  }
  
  public String toString(){
	  String string = "Manufacturer Order ID: " + id + " | Supplier: " + supplier.toString() + "| Total Cost: $" + totalCost;
	  
	  return string;
  }
	
}
