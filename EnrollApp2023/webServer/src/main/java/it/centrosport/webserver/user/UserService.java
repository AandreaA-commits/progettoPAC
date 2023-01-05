package it.centrosport.webserver.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository user) {
		this.userRepository = user;
	}
	
	public List<User> getUsers(){
		return userRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
	}
	
	public User getUser(String id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		
		return user.get();
	}
	
	private User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(!user.isPresent()) return null;
		
		return user.get();
	}
	
	public User signUp(User newUser) {
		var user = getUserByEmail(newUser.getEmail());
		
		if(user != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mail already used");
		
		//Codifica password?
		
		return userRepository.save(newUser);
	}
	
	public User login(User loggingUser) {
		var user = getUserByEmail(loggingUser.getEmail());
		
		if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with this email found");
		
		if(loggingUser.getPassword().equals(user.getPassword())) {
			user.setLoggedIn(true);
			return userRepository.save(user);
		}
		else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
	}
	
	public void logout(User loggingOutUser) {
		var user = getUserByEmail(loggingOutUser.getEmail());
		
		if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with this email found");
		
		if (user.isLoggedIn()) {
			user.setLoggedIn(false);
			userRepository.save(user);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not logged in");
		}
	}
	
	public void deleteUser(String id) {
		Optional<User> userToDelete = userRepository.findById(id);
		
		if(!userToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
		
		userRepository.delete(userToDelete.get());
	}
	
	
}
