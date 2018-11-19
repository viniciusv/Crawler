package com.crawler.api.service;

import org.springframework.stereotype.Component;

import com.crawler.api.domain.Feed;

@Component
public interface CrawlerService {
	
	Feed returnFeed();
}
