package com.chattersphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.chattersphere.config.JwtProvider;
import com.chattersphere.models.User;
import com.chattersphere.repository.UserRepository;
import com.chattersphere.request.LoginRequest;
import com.chattersphere.response.AuthResponse;
import com.chattersphere.service.CustomerUserDetailsService;
import com.chattersphere.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
//		User newUser = new User();
//		newUser.setEmail(user.getEmail());
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastName(user.getLastName());
//		newUser.setPassword(user.getPassword());
//		newUser.setId(user.getId());
//		
//		User savedUser = userRepository.save(newUser);
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if(isExist != null) {
			throw new Exception("this email is alrady in use with another account");
		}
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//		newUser.setId(user.getId());
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail() , savedUser.getPassword());
		
		String token = JwtProvider.generateToKen(authentication);
		
		AuthResponse res = new AuthResponse(token, "Register Sucess");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		String token = JwtProvider.generateToKen(authentication);
		
		AuthResponse res = new AuthResponse(token, "Login Sucess");
		
		return res;
	}

	private Authentication authenticate(String email, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
