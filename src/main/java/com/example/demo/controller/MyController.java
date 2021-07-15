package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/")
	public String home() {
		return "Welcome Home";
	}
	
	
	@GetMapping("/user")
	public String userRole() {
		return "Welcome User";
	}
	
	@GetMapping("/admin")
	public String adminRole() {
		return "Welcome Admin";
	}
	
	@GetMapping("/unauthorized")
	public String unauthorized() {
		return "Not Authorized";
	}
}
