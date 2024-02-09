package JDBC.Restaurant.UserLogin;

public class UserLogin {

	private static String Email;
	private String Password;
	
	
	public static String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	public UserLogin(String Email, String Password) {
		super();
		UserLogin.Email = Email;
		this.Password = Password;
	}
}
