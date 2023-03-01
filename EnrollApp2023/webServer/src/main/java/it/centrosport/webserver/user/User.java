package it.centrosport.webserver.user;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String idUser;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean loggedIn;
	private UserRoles userRole;
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.loggedIn = false;		
	}
	
	public User() {}

	public String getId() {
		return idUser;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public UserRoles getUserRole() {
		return userRole;
	}
	
	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}
	
	
}
