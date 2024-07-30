package com.chattersphere.service;

import java.util.List;

import com.chattersphere.exceptions.UserException;
import com.chattersphere.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserByEmail(String user);
	
	public User followUser(Integer userId1, Integer userId2) throws UserException;
	
	public User updateUser(User user, Integer UserId) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);

}
