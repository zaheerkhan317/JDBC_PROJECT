package JDBC.Restaurant.UserRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRegistrationDOAImpl implements UserRegistrationDOA {
	String JDBC_URL = "jdbc:mysql://localhost:3306/restaurant";
    String USERNAME = "root";
    String PASSWORD = "Admin123";
    
    

    @Override
    public void InsertData(UserRegistration UserReg) {
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    	    ResultSet resultSet = conn.getMetaData().getTables(null, null, "users", null);
    	    if (resultSet.next()) { // Table doesn't exist, create it
    	        String CreateTable = "CREATE TABLE Users ("
    	                + "User_ID INT AUTO_INCREMENT PRIMARY KEY,"
    	                + "Username VARCHAR(255),"
    	                + "Email VARCHAR(255) UNIQUE,"
                        + "Phone BIGINT UNIQUE,"
    	                + "Password VARCHAR(255))";
    	        try (PreparedStatement stmts = conn.prepareStatement(CreateTable)) {
    	            stmts.executeUpdate();	// Table create
    	            System.out.println("");
    	            System.out.println("===============================");
    	            System.out.println("Table created successfully!!!!!");
    	            System.out.println("===============================");
    	            System.out.println("");
    	        }
    	    }
    	} catch (SQLException e) {
    		System.out.println("");
    		System.out.println("=========================");
    		System.out.println("Table already exists!!!!!");
    		System.out.println("=========================");
    		System.out.println("");
    	}


        // Insert data regardless of whether the table existed or was just created
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String InsertTable = "INSERT INTO Users (Username, Email, Phone, Password) VALUES(?, ?, ?, ?)";
            try (PreparedStatement stmts = conn.prepareStatement(InsertTable)) {
                stmts.setString(1, UserReg.getUsername());
                stmts.setString(2, UserReg.getEmail());
                stmts.setLong(3, UserReg.getPhone()); // setLong for BIGINT
                stmts.setString(4, UserReg.getPassword());
                int rowsAffected = stmts.executeUpdate();
                if (rowsAffected > 0) {
                	System.out.println("");
                	System.out.println("==========================");
                	System.out.println("Registration successful!!!");
                	System.out.println("==========================");
                	System.out.println("");
                	mainMenu();
                } else {
                	System.out.println("");
                	System.out.println("======================");
                    System.out.println("Failed to insert data.");
                    System.out.println("======================");
                    System.out.println("");
                    mainMenu();
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
        	System.out.println("");
        	System.out.println("===============================================");
            System.out.println("Duplicate entry: Email or phone already exists.");
            System.out.println("===============================================");
            System.out.println("");
            mainMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void mainMenu() {
        // Display menu options
    	System.out.println("Select an option:");
	    System.out.println("");
	    System.out.println("1. Register");
	    System.out.println("2. User Login");
	    System.out.println("3. Admin Login");
	    System.out.println("0. Exit");
	    System.out.println("");
        
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.println("");
        switch (option) {
            case 1:
                Main.register(sc);
                break;
            case 2:
                Main.Userlogin(sc);
                break;
            case 3:
            	Main.AdminLogin(sc);
            case 0:
            	System.out.println("");
            	System.out.println("====================");
                System.out.println("Exiting the program.");
                System.out.println("====================");
                System.out.println("");
                System.exit(0); // Exit the program
                break;
            default:
            	System.out.println("");
            	System.out.println("================================================");
                System.out.println("Invalid option. Please select either 1, 2 or 0.");
                System.out.println("================================================");
                System.out.println("");
                mainMenu(); // Display main menu again for invalid option
        }
    }


}
