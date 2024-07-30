package com.chattersphere.service;

import java.util.List;



import com.chattersphere.models.Chat;
import com.chattersphere.models.User;

public interface ChatService {

	public Chat createChat(User reqUser,User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}
