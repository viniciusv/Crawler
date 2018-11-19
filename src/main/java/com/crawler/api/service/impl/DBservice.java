package com.crawler.api.service.impl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.api.model.UserApp;
import com.crawler.api.service.UserService;

@Service
public class DBservice {

	@Autowired
	private UserService userService;
	
	public void instantiateTestDatabase() throws ParseException {

		UserApp user = new UserApp(null, "userF", "userF", "123AbC");
		UserApp userSecond = new UserApp(null, "userS", "userS", "456AeF");
		
		userService.saveUser(user);
		userService.saveUser(userSecond);
	}
}
