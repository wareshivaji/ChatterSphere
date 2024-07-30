package com.chattersphere.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chattersphere.exceptions.UserException;
import com.chattersphere.models.User;
import com.chattersphere.repository.UserRepository;
import com.chattersphere.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
//		List<User> users=new ArrayList<>();
//		 
//		User user1 = new User(1,"Shivaji", "Ware", "shiv@gmail.com", "12345");
//		User user2 = new User(2,"Jam", "Nissan", "Nisj@gmail.com", "12345");
//		
//		users.add(user1);
//		users.add(user2);
//		
//		return users;
		
		List<User>users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId")Integer id) throws UserException {
		
		
		 
//		User user1 = new User(1,"Shivaji", "Ware", "shiv@gmail.com", "12345");
//		User user2 = new User(2,"Jam", "Nissan", "Nisj@gmail.com", "12345");
//		user1.setId(id);
		
//		users.add(user2);
		
//		Optional<User> user = userRepository.findById(id);
//		
//		if(user.isPresent()) {
//			return user.get();
//		}
//		 
//		throw new Exception("user doesnot exist with user id "+ id);
		
		User user =userService.findUserById(id);
		return user;
	}
	

	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws UserException {
		
//		Optional<User> user1 = userRepository.findById(userId);
//		
//		if(user1.isEmpty()) {
//			throw new Exception("user does not exist with id "+ userId);
//		}
//		
//		User oldUser = user1.get();
//		
//		if(user.getFirstName() != null) {
//			oldUser.setFirstName(user.getFirstName());
//		}
//		if(user.getLastName() != null) {
//			oldUser.setLastName(user.getLastName());
//		}
//		if(user.getEmail() != null) {
//			oldUser.setEmail(user.getEmail());
//		}
//		User updatedUser = userRepository.save(oldUser);
//		
////		User user1 = new User(1,"Shivaji", "Ware", "shiv@gmail.com", "12345");
////		
////		if(user.getFirstName()!= null) {
////			user1.setFirstName(user.getFirstName());
////		}
////		if(user.getLastName()!= null) {
////			user1.setLastName(user.getLastName());
////		}
////		if(user.getEmail()!= null) {
////			user1.setEmail(user.getEmail());
////		}
		 
//		return updatedUser;
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
	
	}
	
//	@DeleteMapping("users/{userId}")
//	public String deleteUser(@PathVariable ("userId") Integer userId) throws Exception {
//		
//		Optional<User> user = userRepository.findById(userId);
//		
//		if(user.isEmpty()) {
//			throw new Exception("user does not exist with id "+ userId);
//		}
//		
//		userRepository.delete(user.get());
//		
//		return "user successfully deleted with id " + userId;
//	}
	
	@PutMapping("/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws UserException {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user= userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users= userService.searchUser(query);
		
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
//		System.out.println("jwt------"+jwt);
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		
		return user;
	}
}
