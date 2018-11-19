package com.crawler.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crawler.api.model.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Integer>{

	UserApp findByUserName(String userName);
}
