package com.crawler.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crawler.api.domain.Description;
import com.crawler.api.domain.Feed;
import com.crawler.api.domain.Item;
import com.crawler.api.exceptions.JsoupConnectErrorException;
import com.crawler.api.service.CrawlerService;

@Service
public class CrawlerServiceImpl implements CrawlerService {
	
	static Logger logger = Logger.getLogger(CrawlerServiceImpl.class);

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
	public Feed returnFeed()  {
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
		logger.info("Feed created successfully.");
		return feed;
	}

	private void createDescription(String type, List<String> value, List<Description> listContent) {
		Description content = new Description();
		content.setType(type);
		content.setContent(value);
		listContent.add(content);
	}
	
	private void createDescription(String type, String value, List<Description> listContent) {
		Description content = new Description();
		content.setType(type);
		content.addContent(value);
		listContent.add(content);
	}
	
	private List<Description> returnListDescriptions(Document docDescription) {
		List<Description> listContent = new ArrayList<Description>();
		
		Elements imgs = docDescription.select(SELECT_IMG);
		imgs.stream().forEach(img ->{
			this.createDescription(SELECT_IMG_TYPE, img.attr(SELECT_IMG_ATTR), listContent);
		});
		
		Elements ps = docDescription.select(SELECT_P);
		ps.stream().filter(p->!p.text().isEmpty()).forEach(p->{
			this.createDescription(SELECT_P_TYPE, p.text(), listContent);
		});

		Elements links = docDescription.select(SELECT_LINK);
		List<String> hrefs = links.stream().map(a -> a.attr(SELECT_LINK_ATTR)).collect(Collectors.toList());
		this.createDescription(SELECT_LINK_TYPE, hrefs, listContent);
			
		return listContent;
	}

	private Document returnNewDocument() {
		try {
			logger.info("Connect url: " + this.baseUrl);
			return Jsoup.connect(this.baseUrl).get();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new JsoupConnectErrorException("Erro ao se conectar com a url: " + this.baseUrl);
		}
	}
	
	
	/*
	 ********************************************************************************
	 **                                                                            **
	 ** Uma segunda solução para o crawler é usando RECURSIVIDADE                  **
	 ** A solução está incompleta, só falta a parte da descrição do item           ** 
	 ** Assim eu retorno o feed conforme a aplicação vai lendo a página, nó por nó.**
	 **                                                                            **
	 ********************************************************************************/
	
	
	public void call() {
		Document doc = this.returnNewDocument();
		for(Node no : doc.childNodes())
			this.returnFeed2(no);

	}
	
	private void returnFeed2(Node no) {
				
		if(no.parent().nodeName().equals("item") || no.parent().nodeName().equals("description")) {
			if(no.nodeName().equals("title")) {
				if(no instanceof Element) {
					Element title = (Element)no;
					System.out.println("TITULO : " + title.text()+"\n");
				}				
			}
			if(no.nodeName().equals("description") || no.parent().nodeName().equals("description")) {
				if(no instanceof Element) {
					System.out.println("desc : " + no.nodeName()+"\n");
					Document docDescription = Jsoup.parse(no.outerHtml(), "", Parser.htmlParser());
					for(Node no2 : docDescription.childNodes()) {
						System.out.println("desc no: " + no2.nodeName()+"\n");
					}
						
				}	
			}
			if(no.nodeName().equals("link")) {
				if(no instanceof Element) {
					Element link = (Element)no;
					System.out.println("LINK : " + link.text()+"\n");
				}					
			}
			
		}else {
			if(no.childNodeSize() != 0) {
				for(Node no2 : no.childNodes())
					this.returnFeed2(no2);
			}
		}
	}

}
