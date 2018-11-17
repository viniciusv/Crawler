package com.crawler.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crawler.api.dto.Description;
import com.crawler.api.dto.Feed;
import com.crawler.api.dto.Item;
import com.crawler.api.service.CrawlerService;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	@Value("${url.base.globo.api}")
	private String baseUrl;
	
	private static final String SELECT_ITEM = "item";
	private static final String TAG_TITLE = "title";
	private static final String TAG_LINK = "link";
	private static final String TAG_DESCRIPTION = "description";
	private static final String SELECT_IMG = "div > img";
	private static final String SELECT_IMG_TYPE = "image";
	private static final String SELECT_IMG_ATTR = "src";
	private static final String SELECT_P = "p";
	private static final String SELECT_P_TYPE = "text";
	private static final String SELECT_LINK = "div > ul > li > a";
	private static final String SELECT_LINK_TYPE = "links";
	private static final String SELECT_LINK_ATTR = "href";
	

	@Override
	public Feed returnFeed() {
		Document doc = this.returnNewDocument();

		Feed feed = new Feed();
		for (Element e : doc.select(SELECT_ITEM)) {
			Item item = new Item();

			item.setTitle(e.getElementsByTag(TAG_TITLE).text());
			item.setLink(e.getElementsByTag(TAG_LINK).text());
			Document docDescription = Jsoup.parse(e.getElementsByTag(TAG_DESCRIPTION).text(), "", Parser.htmlParser());
			item.setDescriptions(this.returnListDescriptions(docDescription));

			feed.addItem(item);
		}
		return feed;
	}

	private List<Description> returnListDescriptions(Document docDescription) {
		List<Description> listContent = new ArrayList<Description>();
		Description content;
		
		Elements imgs = docDescription.select(SELECT_IMG);
		for (Element img : imgs) {
			content = new Description();
			content.setType(SELECT_IMG_TYPE);
			content.addContent(img.attr(SELECT_IMG_ATTR));
			listContent.add(content);
		}

		Elements ps = docDescription.select(SELECT_P);
		for (Element p : ps) {
			if(!p.text().isEmpty())  {
				content = new Description();
				content.setType(SELECT_P_TYPE);
				content.addContent(p.text());
				listContent.add(content);	
			}		    	
		}

		Elements links = docDescription.select(SELECT_LINK);
		if(links.size() > 0) {
			content = new Description();
			content.setType(SELECT_LINK_TYPE);
			for (Element ul : links) {
				content.addContent(ul.attr(SELECT_LINK_ATTR));
				listContent.add(content);
			}
		}
			

		return listContent;
	}

	private Document returnNewDocument() {
		try {
			return Jsoup.connect(baseUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
