package com.chattersphere.service;

import java.util.List;

import com.chattersphere.models.Story;
import com.chattersphere.models.User;

public interface StoryService {

	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
		
}
