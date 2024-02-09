package JDBC.Restaurant.Admin;

import java.util.Scanner;

import JDBC.Restaurant.Orders.Orders;
import JDBC.Restaurant.UserRegistration.Main;
import JDBC.Restaurant.UserRegistration.UserRegistration;
import JDBC.Restaurant.UserRegistration.UserRegistrationDOA;
import JDBC.Restaurant.UserRegistration.UserRegistrationDOAImpl;

public class CRUD {
	
	public static void CrudOp() {
		System.out.println("Select an option:");
		System.out.println("");
	    System.out.println("1. Add the user");
	    System.out.println("2. Delete the user");
	    System.out.println("3. Update user details");
	    System.out.println("4. Modify Order");
	    System.out.println("5. Cancel Order");
	    System.out.println("6. Get all Orders");
	    System.out.println("7. Get all Users");
	    System.out.println("8. Get Orders by name");
	    System.out.println("0. Logout");
	    System.out.println("");
	    
	    Scanner sc = new Scanner(System.in);
	    int option = sc.nextInt();
	    System.out.println("");
	    sc.nextLine(); // Consume newline
	
	    switch (option) {
	        case 1:
	            AddUser();
	            break;
	        case 2:
	            DeleteUser();
	            break;
	        case 3:
	            UpdateUser();
	            break;
	        case 4:
	            ModifyOrder();
	            break;
	        case 5:
	        	CancelOrder();
	        	break;
	        case 6:
	        	GetAllOrders();
	        	break;
	        	
	        case 7:
	        	GetAllUsers();
	        	break;
	        case 8:
	        	GetOrdersByName();
	        	break;
	        case 0:
	        	System.out.println("");
	            System.out.println("===========================");
	            System.out.println("Logging out........");
	            System.out.println("Logged Out Successfully!!!!");
	            System.out.println("===========================");
	            System.out.println("");
	             // Exit the program
	            Main.main(null);
	            break;
	        default:
	            System.out.println("Invalid option. Please select a valid option.");
	            Main.Logins(); // Display main menu again for invalid option
	    }
	}
	static Scanner sc = new Scanner(System.in);
	
	public static void AddUser() {
		
		System.out.print("Enter your Name : ");
        String name = sc.nextLine();

        System.out.print("Enter your Email : ");
        String email = sc.nextLine();

        System.out.print("Enter your Number : ");
        long phone = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter your Password : ");
        String password = sc.nextLine();
        
        System.out.println("");
        UserRegistrationDOA userReg = new UserRegistrationDOAImpl();
        UserRegistration user = new UserRegistration(name, email, phone, password);
        userReg.InsertData(user);
		
	}

	public static void UpdateUser() {
		System.out.print("Enter your Name : ");
        String name = sc.nextLine();

        System.out.print("Enter your Email : ");
        String email = sc.nextLine();

        System.out.print("Enter your Number : ");
        long phone = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter your Password : ");
        String password = sc.nextLine();
        
        System.out.println("");
        AdminDOA userReg = new AdminDOAimpl();
        UserRegistration user = new UserRegistration(name, email, phone, password);
        userReg.UpdateUser(user);
		
	}
	
	public static void DeleteUser() {
		
		
		System.out.print("Enter your Number : ");
        long phone = sc.nextLong();
        sc.nextLine();
        
        System.out.println("");
        
        AdminDOA userReg = new AdminDOAimpl();
        UserRegistration user = new UserRegistration(phone);
        userReg.DeleteUser(user);
		
	}
	
	public static void ModifyOrder() {
		
		System.out.print("Enter your ORDER_ITEM : ");
        String ordered_item = sc.nextLine();

        System.out.print("Enter your QUANTITY : ");
        int qunatity = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter your PRICE : ");
        int price = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter your ORDER_ID to modify : ");
        int OrderID = sc.nextInt();
        sc.nextLine();
        
        System.out.println("");
        
        AdminDOA userReg = new AdminDOAimpl();
        Orders od = new Orders(OrderID, qunatity, price, ordered_item);
        userReg.ModifyOrder(od);
        
		
	}
	
	public static void CancelOrder() {
		
		System.out.print("Enter the ORDER_ID : ");
        int orderID = sc.nextInt();
        sc.nextLine();
        
        System.out.println("");
        
        AdminDOA userReg = new AdminDOAimpl();
        Orders od = new Orders(orderID);
        userReg.cancelOrder(od);
	}
	
	
	public static void GetAllOrders() {
		
        
        AdminDOA userReg = new AdminDOAimpl();
        Orders od = new Orders();
        userReg.GetAllOrders(od);
	}
	
	public static void GetAllUsers() {
		
        
        AdminDOA userReg = new AdminDOAimpl();
        UserRegistration ur = new UserRegistration();
        userReg.GetAllUsers(ur);
	}
	
	
	public static void GetOrdersByName() {
		
		System.out.print("Enter the username : ");
        String username = sc.nextLine();
        
		AdminDOA userReg = new AdminDOAimpl();
        Orders od = new Orders(username);
        userReg.GetOrdersByName(od);
	}




	
	
	
}
