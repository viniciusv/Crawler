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

		UserApp user = new UserApp(null, "userFirst", "userFirst", "123AbC8jO");
		UserApp userSecond = new UserApp(null, "userSecond", "userSecond", "456AeFLe1");
		
		userService.saveUser(user);
		userService.saveUser(userSecond);
	}
}
