package com.example.inhouses.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class inhouseController {
	
	@Value ("${app.name}")
	private String appName;
	
	//display hello world
	@GetMapping({"/"})
	public String sayHello() {
		return "Hello World";
	}
	
	@GetMapping({"/appname"})
	public String getAppName() {
		return appName;
	}
}
