package com.crawler.api.service;

import org.springframework.stereotype.Component;

import com.crawler.api.model.UserApp;

@Component
public interface UserService {

	void saveUser(UserApp user);
	
	UserApp findByUserName(String userName);
}
