package com.crawler.api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crawler.api.model.User;
import com.crawler.api.repository.UserRepository;
import com.crawler.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void saveUser(User user) {
		String passwordEncode = this.bCryptPasswordEncoder.encode(user.getPassword()); 
		user.setPassword(passwordEncode);
		this.userRepository.save(user);
	}

}
