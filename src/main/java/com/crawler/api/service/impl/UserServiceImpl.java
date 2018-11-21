package com.crawler.api.service.impl;


import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crawler.api.exceptions.UsernameExistException;
import com.crawler.api.model.UserApp;
import com.crawler.api.repository.UserRepository;
import com.crawler.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void saveUser(@Valid UserApp user) {
		if(findByUserName(user.getUserName())==null) {
			String passwordEncode = this.bCryptPasswordEncoder.encode(user.getPassword()); 
			logger.info("Encoder password" );
			user.setPassword(passwordEncode);
			this.userRepository.save(user);	
			logger.info("created user : "+user.getUserName());
		}else {
			throw new UsernameExistException("Exist user with userName " + user.getUserName());
		}
	}

	@Override
	public UserApp findByUserName(String userName) {
		logger.info("find user : " + userName);
		UserApp findUser = this.userRepository.findByUserName(userName);
		return findUser;
	}

}
