import java.util.*;
import java.text.*;
import java.io.*;

public class Shipment implements Serializable{
    private static final long serialVersionUID = 1L;
    private Manufacturer manufacturer;
    private Product product;
    private float supplierPrice;
    
    public Shipment(Manufacturer manufacturer, Product product, float supplierPrice){
        this.manufacturer = manufacturer;
        this.product = product;
        this.supplierPrice = supplierPrice;
    }
    
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public float getSupplierPrice() {
        return supplierPrice;
    }
    
    public void setSupplierPrice(float s) {
        supplierPrice = s;
    }

    public boolean recieveShipment(Product product)
    {
        products.add(product);
        return true;
    }
}
