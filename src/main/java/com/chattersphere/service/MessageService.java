package com.chattersphere.service;

import java.util.List;

import com.chattersphere.models.Chat;
import com.chattersphere.models.Message;
import com.chattersphere.models.User;

public interface MessageService {
 
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
