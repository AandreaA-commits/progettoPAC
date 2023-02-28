package it.centrosport.webserver.user;

import java.util.List;

public interface UserServiceIF {
	public List<User> getUsers();

	public User getUser(String id);

	public User signUp(User newUser);

	public User login(User loggingInUser);

	public void logout(User loggingOutUser);

	public void deleteUser(String id);

}
