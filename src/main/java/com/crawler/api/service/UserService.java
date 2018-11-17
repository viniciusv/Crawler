package com.crawler.api.service;

import org.springframework.stereotype.Component;

import com.crawler.api.model.User;

@Component
public interface UserService {

	void saveUser(User user);
}
