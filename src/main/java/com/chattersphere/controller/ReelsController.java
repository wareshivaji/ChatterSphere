package com.chattersphere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.chattersphere.models.Reels;
import com.chattersphere.models.User;
import com.chattersphere.service.ReelsService;
import com.chattersphere.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization")String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reels createdReels = reelsService.createReel(reel, reqUser);
		
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> createReels() {
		
		List<Reels> reels = reelsService.findAllReels();
		
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {
		
		List<Reels> reels = reelsService.findUserReels(userId);
		
		return reels;
	}
}
