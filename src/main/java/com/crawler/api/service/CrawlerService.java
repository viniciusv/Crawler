package com.crawler.api.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crawler.api.dto.Feed;

@Component
public interface CrawlerService {
	
	Feed returnFeed();
}
