package com.crawler.api.service;

import org.springframework.stereotype.Component;

import com.crawler.api.dto.Feed;

@Component
public interface CrawlerService {
	
	Feed returnFeed();
}
