package com.crawler.api.service.impl;


import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crawler.api.model.UserApp;
import com.crawler.api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName){
		UserApp user = userRepository.findByUserName(userName);
		if(user == null) {
			logger.info("UserName not found " + userName);
			throw new UsernameNotFoundException(userName);
		}
		return new User(user.getUserName(), user.getPassword(), Collections.EMPTY_LIST);
	}

}
