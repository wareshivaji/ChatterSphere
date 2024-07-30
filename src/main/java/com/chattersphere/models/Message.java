package com.chattersphere.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String content;
	
//	@ManyToOne
	private String Image;
	
	@JsonIgnore
	@ManyToOne
	private Chat chat;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime timeStamp;

}
