package JDBC.Restaurant.UserLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import JDBC.Restaurant.Orders.*;

public class UserLoginDOAImpl implements UserLoginDOA{
	
	String JDBC_URL = "jdbc:mysql://localhost:3306/restaurant";
    String USERNAME = "root";
    String PASSWORD = "Admin123";
    
    @Override
    public void ValidateLogin(UserLogin login) {
    	try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, UserLogin.getEmail());
                statement.setString(2, login.getPassword());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // User exists with provided credentials
        	            System.out.println("");
        	            System.out.println("=====================");
                        System.out.println("Login Successfull!!!!");
                        System.out.println("=====================");
        	            System.out.println("");
                        MainMenu.mainMenu();
                        
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

}
