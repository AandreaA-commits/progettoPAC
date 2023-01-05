package it.centrosport.webserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "users")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	private User dtoToEntity(UserDto dto) {
		var user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail().toLowerCase());
		user.setPassword(dto.getPassword());
		return user;
	}
	
	@GetMapping
	public Iterable<User> getUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("{userId}")
	public User getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping("signUp")
	public User signUp(@RequestBody UserDto dto) {
		var user = dtoToEntity(dto);
		return userService.signUp(user);
	}
	
	@PostMapping("login")
	public User login(@RequestBody UserDto dto) {
		var user = dtoToEntity(dto);
		return userService.login(user);
	}
	
	@PostMapping("logout")
	public String logout(@RequestBody UserDto dto) {
		var user = dtoToEntity(dto);
		userService.logout(user);
		return "Logged out";
	}
	
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable("userId") String userId) {
		userService.deleteUser(userId);
	}
}
