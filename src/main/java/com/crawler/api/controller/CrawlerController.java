package com.crawler.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.api.dto.Feed;
import com.crawler.api.service.CrawlerService;

@RestController
@RequestMapping(value="/crawler")
public class CrawlerController {
	@Autowired
	private CrawlerService crawlerService;
	
	@GetMapping
	public ResponseEntity<Feed> getJsonFeed() throws IOException {
		
		Feed feed = this.crawlerService.returnFeed();
		return ResponseEntity.ok().body(feed);
	}
}
