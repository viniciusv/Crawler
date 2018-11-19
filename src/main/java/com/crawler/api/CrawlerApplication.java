package com.crawler.api;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crawler.api.service.impl.DBservice;

@SpringBootApplication
public class CrawlerApplication {

	@Autowired
	private DBservice dbService;
	
	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
	}
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
}
