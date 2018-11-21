package com.crawler.integration.test.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.CrawlerApplicationTests;
import com.crawler.api.domain.Feed;
import com.crawler.api.service.CrawlerService;

public class CrawlerServiceIntegrationTest  extends CrawlerApplicationTests {

	@Autowired
	private CrawlerService crawlerService;
	
	@Test
	public void testGetJsonFeed() {
		Feed feed = this.crawlerService.returnFeed();
		assertThat(feed).isNotNull();
	}
}
