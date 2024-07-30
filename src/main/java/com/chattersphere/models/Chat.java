package com.chattersphere.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
public class Chat {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String chat_name;
	
	private String chat_image;
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	private LocalDateTime timestamp;
	
	@OneToMany(mappedBy = "chat")
	private List<Message> messages = new ArrayList<>();
}
