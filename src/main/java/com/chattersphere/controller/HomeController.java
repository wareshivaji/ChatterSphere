package com.chattersphere.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homeControllerHandler() {
		return "this is home Controller";
	}
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home Controller 2";
	}
	
	@GetMapping("/chattersphere")
	public String homeControllerHandler3() {
		return "Hello World";
	}
}
 