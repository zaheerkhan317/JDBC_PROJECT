package JDBC.Restaurant.UserRegistration;

public class UserRegistration {
	
	private String Username;
	private String Email;
	private long Phone;
	private String Password;
	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public long getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	public UserRegistration(String Username, String Email, long Phone, String Password) {
		super();
		this.Username = Username;
		this.Email = Email;
		this.Phone = Phone;
		this.Password = Password;
	}
	
	
	public UserRegistration(long Phone) {
		this.Phone = Phone;
	}
	
	public UserRegistration() {
		
	}
	
	
}
