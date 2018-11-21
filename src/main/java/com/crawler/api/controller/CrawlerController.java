package com.crawler.api.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.api.domain.Feed;
import com.crawler.api.service.CrawlerService;

@RestController
@RequestMapping(value="/crawler")
public class CrawlerController {
	
	static Logger logger = Logger.getLogger(CrawlerController.class);
	
	@Autowired
	private CrawlerService crawlerService;
	
	@GetMapping
	public ResponseEntity<Feed> getJsonFeed() {
		Feed feed = this.crawlerService.returnFeed();
		return ResponseEntity.ok().body(feed);
	}
}
