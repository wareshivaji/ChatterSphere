package com.chattersphere.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private String message;
	
	private String error;
	
	private LocalDateTime timeStamp; 
}
