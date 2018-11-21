package com.crawler.integration.test.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.crawler.CrawlerApplicationTests;
import com.crawler.api.controller.CrawlerController;

public class CrawlerControllerIntegrationTest extends CrawlerApplicationTests{

	private MockMvc mockMvc;

	@Autowired
	CrawlerController crawlerController;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.crawlerController).build();// Standalone context
	}
	
    @Test
    public void testGetJsonFeed() throws Exception {
        this.mockMvc.perform(get("/crawler")
        	.contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.items").isNotEmpty());
    }
}
