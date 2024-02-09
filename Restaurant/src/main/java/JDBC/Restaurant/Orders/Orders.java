package JDBC.Restaurant.Orders;

public class Orders {
	
	private String st;
	private int price;
    private int quantity;
    private int OrderID;
	private String username;
    
    public int getPrice() {
    	return price;
    }
    
    public void setPrice(int price) {
    	this.price = price;
    }
    
	public String getOrderItem() {
		return st;
	}
	
	public void setOrderItem(String st) {
		this.st = st;
	}
	
	
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int OrderID() {
    	return OrderID;
    }
    
    public void OrderID(int OrderID) {
    	this.OrderID = OrderID;
    }
    
    public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Orders(int price, String st,  int quantity) {
    	super();
    	this.price = price;
    	this.st = st;
    	this.quantity = quantity;
    }
	
	public Orders(int OrderID, int quantity, int price, String st) {
    	super();
    	this.quantity = quantity;
    	this.st = st;
    	this.price = price;
    	this.OrderID = OrderID;
    }
	
	public Orders(int OrderID) {
		this.OrderID = OrderID;
	}
	
	public Orders() {
		
	}
	
	public Orders(String username) {
		this.username = username;
	}
}
