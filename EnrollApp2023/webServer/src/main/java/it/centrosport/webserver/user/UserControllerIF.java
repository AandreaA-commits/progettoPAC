package it.centrosport.webserver.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerIF {

	public Iterable<User> getUsers();

	public User getUser(@PathVariable String userId);

	public User signUp(@Validated @RequestBody UserDto dto);

	public User login(@Validated @RequestBody UserDto dto);

	public String logout(@Validated @RequestBody UserDto dto);

	public void deleteUser(@PathVariable("userId") String userId);

}
