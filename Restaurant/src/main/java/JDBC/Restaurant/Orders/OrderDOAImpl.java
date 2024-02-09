package JDBC.Restaurant.Orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.Restaurant.UserLogin.UserLogin;
import JDBC.Restaurant.UserLogin.UserLoginDOA;
import JDBC.Restaurant.UserRegistration.UserRegistration;

public class OrderDOAImpl implements OrderDOA{
	private String email;
	private String password;
	String JDBC_URL = "jdbc:mysql://localhost:3306/restaurant";
    String USERNAME = "root";
    String PASSWORD = "Admin123";
    
    
    
    String em = UserLogin.getEmail();
    String pass = password;
	@Override
    public void insertOrder(Orders ord) {
		    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    	    ResultSet resultSet = conn.getMetaData().getTables(null, null, "orders", null);
    	    if (!resultSet.next()) { // Table doesn't exist, create it
    	        String CreateTable = "CREATE TABLE ORDERS ("
    	                + "ORDER_ID INT AUTO_INCREMENT PRIMARY KEY,"
    	                + "ORDER_ITEM VARCHAR(255),"
    	                + "QUANTITY INT,"
    	                + "PRICE INT,"
    	                + "USERNAME VARCHAR(255),"
                        + "Phone BIGINT,"
                        + "ORDER_DATE DATETIME DEFAULT CURRENT_TIMESTAMP)";
    	        try (PreparedStatement stmts = conn.prepareStatement(CreateTable)) {
    	            stmts.executeUpdate();
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
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Retrieve user data from the users table using the provided email
            String selectUserQuery = "SELECT * FROM Users WHERE Email = ?";
            
            try (PreparedStatement selectStmt = conn.prepareStatement(selectUserQuery)) {
                selectStmt.setString(1, em);
                ResultSet resultSet = selectStmt.executeQuery();

                if (resultSet.next()) {
                    // User found, retrieve data
                    String username = resultSet.getString("Username");
                    long phone = resultSet.getLong("Phone");
                    // Now insert this data into the orders table
                    String insertOrderQuery = "INSERT INTO ORDERS (ORDER_ITEM, QUANTITY, PRICE, USERNAME, PHONE, ORDER_DATE) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertOrderQuery)) {
                        insertStmt.setString(1, ord.getOrderItem());
                        insertStmt.setInt(2, ord.getQuantity());
                        insertStmt.setInt(3, ord.getPrice()* ord.getQuantity());
                        insertStmt.setString(4, username);
                        insertStmt.setLong(5, phone);
                        insertStmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime())); // Set the current date
                        int rowsAffected = insertStmt.executeUpdate();
                        if (rowsAffected > 0) {
                        	System.out.println("");
            	            System.out.println("==========================");
                            System.out.println("Order placed successfully!");
                            System.out.println("==========================");
                            System.out.println("");
                            MainMenu.mainMenu();
                        } else {
                        	System.out.println("");
            	            System.out.println("======================");
                            System.out.println("Failed to place order.");
                            System.out.println("======================");
                            System.out.println("");
                        }
                    }
                } else {
                    // User not found
                	System.out.println("");
    	            System.out.println("========================================");
                    System.out.println("User with email " + email + " not found.");
                    System.out.println("========================================");
                    System.out.println("");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	

/*
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
                	System.out.println("Registration successful!!!");
                	mainMenu();
                } else {
                    System.out.println("Failed to insert data.");
                    mainMenu();
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry: Email or phone already exists.");
            mainMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }
    
    
}
