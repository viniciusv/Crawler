package com.crawler.api;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crawler.api.service.impl.DBservice;

@SpringBootApplication
public class CrawlerApplication {

	static Logger logger = Logger.getLogger(CrawlerApplication.class);
	
	@Autowired
	private DBservice dbService;
	
	public static void main(String[] args) {
		logger.info("Init application");
		SpringApplication.run(CrawlerApplication.class, args);
	}
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		logger.info("Insert test data");
		dbService.instantiateTestDatabase();
		return true;
	}
	
	/*@Bean 
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
}
