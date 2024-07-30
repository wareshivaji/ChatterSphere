package com.chattersphere.service;

import java.util.List;

import com.chattersphere.models.Reels;
import com.chattersphere.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;
	
	
}
