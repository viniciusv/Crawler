package com.crawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.crawler.api.CrawlerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class CrawlerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
