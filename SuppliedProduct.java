import java.util.*;
import java.text.*;
import java.io.*;

public class SuppliedProduct implements Serializable{
    private static final long serialVersionUID = 1L;
    private Manufacturer manufacturer;
    private Product product;
    private float supplyPrice;
    
    public SuppliedProduct(Manufacturer manufacturer, Product product, float supplyPrice) {
	       this.manufacturer = manufacturer;
	       this.product = product;
	       this.supplyPrice = supplyPrice;
    }
   
    public Manufacturer getManufacturer() {
    	return manufacturer;
    }
    
    public Product getProduct() {
    	return product;
    }
   
    public float getSupplyPrice() {
    	return supplyPrice;
    }
}
