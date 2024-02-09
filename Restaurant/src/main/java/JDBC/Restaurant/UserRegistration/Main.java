package JDBC.Restaurant.UserRegistration;
import JDBC.Restaurant.UserLogin.*;
import JDBC.Restaurant.Admin.*;
import java.sql.SQLException;
import java.util.*;
public class Main {
	
	// Display menu options
	public static void Logins() {
	    System.out.println("Select an option:");
	    System.out.println("");
	    System.out.println("1. Register");
	    System.out.println("2. User Login");
	    System.out.println("3. Admin Login");
	    System.out.println("0. Exit");
	    System.out.println("");
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("||         WELCOME TO OUR            ||");
        System.out.println("||           RESTAURANT!!!           ||");
        System.out.println("=======================================");
        System.out.println("\n");
        
		
		Logins();

        int option = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.println("");
        switch (option) {
            case 1:
                register(sc);
                break;
            case 2:
                Userlogin(sc);
                break;
                
            case 3:
            	AdminLogin(sc);
            	break;
            case 0:
            	System.out.println("");
            	System.out.println("===========================");
                System.out.println("Thank You. Visit Again!!!!!");
                System.out.println("===========================");
                System.out.println("");
                System.exit(0); // Exit the program
                break;
            default:
            	System.out.println("");
            	System.out.println("====================");
                System.out.println("Invalid option. Please select either 1, 2 or 0.");
                System.out.println("====================");
                System.out.println("");
        }

        sc.close();
    }

	/* ====================================== User Register ================================================== */
	
	

	public static void register(Scanner sc) {

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
	
	/*=========================================== User Register Ends ================================================*/
	
	/*=========================================== User Login Starts =================================================*/

    public static void Userlogin(Scanner sc) {
        System.out.print("Enter your Email : ");
        String email = sc.nextLine();

        System.out.print("Enter your Password : ");
        String password = sc.nextLine();

        UserLoginDOA userLog = new UserLoginDOAImpl();
        userLog.ValidateLogin(new UserLogin(email, password));
        
	}
    
    /*=========================================== User Login Ends ===================================================*/
    
    
    /*=========================================== Admin Login Starts ===============================================*/
    
    public static void AdminLogin(Scanner sc) {
    	AdminDOA adminlog = new AdminDOAimpl();
    	adminlog.CreateTable(new Admin("admin","admin@gmail.com","admin123"));
    	System.out.print("Enter your Email : ");
        String email = sc.nextLine();

        System.out.print("Enter your Password : ");
        String password = sc.nextLine();

        
        
        adminlog.ValidateLogin(new Admin(email, password));
		
	}
    
    /*=========================================== Admin Login Ends =================================================*/
}
