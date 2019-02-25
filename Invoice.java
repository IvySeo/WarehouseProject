import java.util.*;
import java.text.*;
import java.io.*;

//Not needed for stage 1 processes. So this class may be incomplete
public class Invoice implements Serializable{

	private static final long serialVersionUID = 1L;
	private Client client;
	private Order order;
	private float balance;
}
