package com.crawler.api.service.impl;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crawler.api.exceptions.UsernameExistException;
import com.crawler.api.model.UserApp;
import com.crawler.api.repository.UserRepository;
import com.crawler.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void saveUser(@Valid UserApp user) {
		if(findByUserName(user.getUserName())==null) {
			String passwordEncode = this.bCryptPasswordEncoder.encode(user.getPassword()); 
			user.setPassword(passwordEncode);
			this.userRepository.save(user);	
		}else {
			throw new UsernameExistException("Exist user with userName " + user.getUserName());
		}
	}

	@Override
	public UserApp findByUserName(String userName) {
		UserApp findUser = this.userRepository.findByUserName(userName);
		return findUser;
	}

}
