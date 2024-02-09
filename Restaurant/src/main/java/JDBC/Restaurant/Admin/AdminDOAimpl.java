package JDBC.Restaurant.Admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import JDBC.Restaurant.Orders.MainMenu;
import JDBC.Restaurant.Orders.Orders;
import JDBC.Restaurant.UserLogin.UserLogin;
import JDBC.Restaurant.UserRegistration.UserRegistration;

public class AdminDOAimpl implements AdminDOA {
	
	String JDBC_URL = "jdbc:mysql://localhost:3306/restaurant";
    String USERNAME = "root";
    String PASSWORD = "Admin123";
    
    @Override
    public void CreateTable(Admin login) {
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    	    ResultSet resultSet = conn.getMetaData().getTables(null, null, "admin", null);
    	    if (!resultSet.next()) { // Table doesn't exist, create it
    	        String CreateTable = "CREATE TABLE admin ("
    	                + "Admin_ID INT AUTO_INCREMENT PRIMARY KEY,"
    	                + "AdminName VARCHAR(255),"
    	                + "AdminEmail VARCHAR(255) UNIQUE,"
    	                + "AdminPassword VARCHAR(255))";
    	        String InsertData = "INSERT INTO admin (AdminName, AdminEmail, AdminPassword) VALUES (?, ?, ?)";
    	        try (PreparedStatement createStmt = conn.prepareStatement(CreateTable);
    	        	     PreparedStatement insertStmt = conn.prepareStatement(InsertData)) {
    	        	    createStmt.executeUpdate();
    	        	    insertStmt.setString(1, login.getAdminName());
    	        	    insertStmt.setString(2, login.getAdminEmail());
    	        	    insertStmt.setString(3, login.getAdminPassword());
    	        	    insertStmt.executeUpdate();
    	        	    
    	        	    System.out.println("");
        	            System.out.println("=============================================");
    	        	    System.out.println("Table created and data inserted successfully!");
    	        	    System.out.println("=============================================");
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
    }
    
    @Override
    public void ValidateLogin(Admin login) { 	
    	
    	try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM admin WHERE AdminEmail = ? AND AdminPassword = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, login.getAdminEmail());
                statement.setString(2, login.getAdminPassword());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                    	System.out.println("");
        	            System.out.println("=====================");
                        System.out.println("Login Successfull!!!!");
                        System.out.println("=====================");
                        System.out.println("");
                        CRUD.CrudOp();
                        
                    } else {
                        // User does not exist or credentials are incorrect
                    	System.out.println("");
        	            System.out.println("=========================");
                        System.out.println("Email/Password wrong!!!!!");
                        System.out.println("=========================");
                        System.out.println("");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    
    
    @Override
    public void UpdateUser(UserRegistration reg) {
         // Insert data regardless of whether the table existed or was just created
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    	    String UpdateTable = "UPDATE Users SET Username = ?, Phone = ?, Password = ? WHERE Email = ?";
    	    try (PreparedStatement stmts = conn.prepareStatement(UpdateTable)) {
    	        stmts.setString(1, reg.getUsername());
    	        stmts.setLong(2, reg.getPhone()); // setLong for BIGINT
    	        stmts.setString(3, reg.getPassword());
    	        stmts.setString(4, reg.getEmail());
    	        int rowsAffected = stmts.executeUpdate();
    	        if (rowsAffected > 0) {
    	        	System.out.println("");
    	            System.out.println("====================");
    	            System.out.println("Update successful!!!");
    	            System.out.println("====================");
    	            System.out.println("");
    	            CRUD.CrudOp();
    	        } else {
    	        	System.out.println("");
    	            System.out.println("======================");
    	            System.out.println("Failed to update data.");
    	            System.out.println("======================");
    	            System.out.println("");
    	            CRUD.CrudOp();
    	        }
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

    }
    
    
    @Override
    public void DeleteUser(UserRegistration del) {
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    	    String deleteQuery = "DELETE FROM Users WHERE Phone = ?";
    	    try (PreparedStatement stmts = conn.prepareStatement(deleteQuery)) {
    	        stmts.setLong(1, del.getPhone());
    	        int rowsAffected = stmts.executeUpdate();
    	        if (rowsAffected > 0) {
    	        	System.out.println("");
    	            System.out.println("====================");
    	            System.out.println("Delete successful!!!");
    	            System.out.println("====================");
    	            System.out.println("");
    	            CRUD.CrudOp();
    	        } else {
    	        	System.out.println("");
    	            System.out.println("==============");
    	            System.out.println("User not Exist");
    	            System.out.println("==============");
    	            System.out.println("");
    	            CRUD.CrudOp();
    	        }
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    }
    
    @Override
    public void ModifyOrder(Orders ord) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String deleteQuery = "UPDATE Orders SET Quantity = ?, ORDER_ITEM = ?, PRICE = ? WHERE ORDER_ID = ?";
            try (PreparedStatement stmts = conn.prepareStatement(deleteQuery)) {
            	stmts.setInt(1, ord.getQuantity());
                stmts.setString(2, ord.getOrderItem());
                stmts.setInt(3, ord.getQuantity()* ord.getPrice());
                stmts.setInt(4, ord.OrderID());
                int rowsAffected = stmts.executeUpdate();
                if (rowsAffected > 0) {
                	System.out.println("");
    	            System.out.println("====================");
                    System.out.println("Update successful!!!");
                    System.out.println("====================");
                    System.out.println("");
                    CRUD.CrudOp();
                    // Here you can perform further actions after successful deletion
                } else {
                	System.out.println("");
    	            System.out.println("==============================================");
                    System.out.println("No rows were deleted. The order may not exist.");
                    System.out.println("==============================================");
                    System.out.println("");
                    CRUD.CrudOp();
                    // Here you can perform further actions if deletion fails
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void cancelOrder(Orders ord) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String deleteQuery = "DELETE FROM Orders WHERE ORDER_ID = ?";
            try (PreparedStatement stmts = conn.prepareStatement(deleteQuery)) {
                stmts.setInt(1, ord.OrderID());
                int rowsAffected = stmts.executeUpdate();
                if (rowsAffected > 0) {
                	System.out.println("");
    	            System.out.println("==============================");
                    System.out.println("Order canceled successfully!!!");
                    System.out.println("===============================");
                    System.out.println("");
                    CRUD.CrudOp();
                    // Here you can perform further actions after successful deletion
                } else {
                	System.out.println("");
    	            System.out.println("==============================================");
                    System.out.println("No rows were deleted. The order may not exist.");
                    System.out.println("==============================================");
                    System.out.println("");
                    CRUD.CrudOp();
                    // Here you can perform further actions if deletion fails
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void GetAllOrders(Orders od) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM Orders";
            try (PreparedStatement stmts = conn.prepareStatement(selectQuery)) {
                ResultSet resultSet = stmts.executeQuery();
                while (resultSet.next()) {
                    // Process each row in the result set
                    int orderId = resultSet.getInt("ORDER_ID");
                    String orderItem = resultSet.getString("ORDER_ITEM");
                    int quantity = resultSet.getInt("QUANTITY");
                    int price = resultSet.getInt("PRICE");
                    Date orderdate = resultSet.getDate("ORDER_DATE");
                    // You can process the retrieved data as needed, such as printing or storing it
                    
                    System.out.println("");
    	            System.out.println("====================================================================================================================================");
                    System.out.println("Order ID: " + orderId + ", Order Item: " + orderItem + ", Quantity: " + quantity + ", Price: " + price + ", Order Date : "+ orderdate);
                    System.out.println("====================================================================================================================================");
                    System.out.println("");
                }
                // Optionally, you can perform further actions after retrieving all orders
                CRUD.CrudOp();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void GetAllUsers(UserRegistration ur) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM Users";
            try (PreparedStatement stmts = conn.prepareStatement(selectQuery)) {
                ResultSet resultSet = stmts.executeQuery();
                while (resultSet.next()) {
                    // Process each row in the result set
                    int User_ID = resultSet.getInt("User_ID");
                    String Username = resultSet.getString("Username");
                    String Email = resultSet.getString("Email");
                    long Phone = resultSet.getLong("Phone");
                    String Password = resultSet.getString("Password");
                    // You can process the retrieved data as needed, such as printing or storing it
                    
                    System.out.println("");
    	            System.out.println("=======================================================================================================================");
                    System.out.println("User_ID: " + User_ID + ", Username: " + Username + ", Email: " + Email + ", Phone: " + Phone + ", Password: " + Password);
                    System.out.println("=======================================================================================================================");
                    System.out.println("");
                }
                // Optionally, you can perform further actions after retrieving all orders
                CRUD.CrudOp();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void GetOrdersByName(Orders od) {
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM Orders WHERE USERNAME = ?";
            try (PreparedStatement stmts = conn.prepareStatement(selectQuery)) {
            	stmts.setString(1, od.getUsername());
                ResultSet resultSet = stmts.executeQuery();
                while (resultSet.next()) {
                    // Process each row in the result set
                    int orderId = resultSet.getInt("ORDER_ID");
                    String orderItem = resultSet.getString("ORDER_ITEM");
                    int quantity = resultSet.getInt("QUANTITY");
                    int price = resultSet.getInt("PRICE");
                    // You can process the retrieved data as needed, such as printing or storing it
                    
                    System.out.println("");
    	            System.out.println("=====================================================================================================");
                    System.out.println("Order ID: " + orderId + ", Order Item: " + orderItem + ", Quantity: " + quantity + ", Price: " + price);
                    System.out.println("=====================================================================================================");
                    System.out.println("");
                }
                // Optionally, you can perform further actions after retrieving all orders
                CRUD.CrudOp();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}
