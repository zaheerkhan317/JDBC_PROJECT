package JDBC.Restaurant.Admin;

import JDBC.Restaurant.Orders.Orders;
import JDBC.Restaurant.UserLogin.UserLogin;
import JDBC.Restaurant.UserRegistration.UserRegistration;

public interface AdminDOA {
	
	void CreateTable(Admin login);
	void ValidateLogin(Admin login);
	void UpdateUser(UserRegistration reg);
	void DeleteUser(UserRegistration del);
	void ModifyOrder(Orders ord);
	void cancelOrder(Orders ord);
	void GetAllOrders(Orders ord);
	void GetAllUsers(UserRegistration ur);
	void GetOrdersByName(Orders od);
	
}
