package crewling1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import poly.dto.NewsDTO;

public class News_crewling {

	public static void main(String[] args) throws Exception {
		
		


		int page = 1;

		String url = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid2=258&page="+String.valueOf(page);
		Document doc = null;
		
		
		doc = Jsoup.connect(url).get();
		
		Elements element = doc.select("ul.type06_headline");
		Iterator<Element> eList = element.select("li").iterator();
		List<NewsDTO> pList = new ArrayList<NewsDTO>();
		//List<NewsDTO> pList = new ArrayList<NewsDTO>();
		
		while (eList.hasNext()) {
			Element newsInfo = eList.next();
			NewsDTO pDTO = new NewsDTO();
			String news_link = newsInfo.select("dt a").attr("href");
			String news_title = newsInfo.select("dt a").text();
			String news_summary = newsInfo.select("dd span.lede").text();
			String news_reporter = newsInfo.select("dd span.writing").text();
			String news_regdate = newsInfo.select("dd span.date").text();
			

			
			
			
			pDTO.setNews_summary(news_summary);
			pDTO.setNews_link(news_link);
			pDTO.setNews_reporter(news_reporter);
			pDTO.setNews_title(news_title);
			pDTO.setNews_regdate(news_regdate);
			
			System.out.println("news_link:"+pDTO.getNews_link());
			System.out.println("news_title:"+pDTO.getNews_title());
			System.out.println("news_summary:"+pDTO.getNews_summary());
			System.out.println("news_reporter:"+pDTO.getNews_reporter());
			System.out.println("news_regdate:"+pDTO.getNews_regdate());
			
			url = news_link;
			doc = Jsoup.connect(url).get();
			
			Elements content = doc.select("div#articleBodyContents");
			
			String news_contents = content.text();
			pDTO.setNews_contents(news_contents);
			
			
			System.out.println("news_contents:"+pDTO.getNews_contents());
			
			pList.add(pDTO);
			pDTO = null;
		}
	

	
		}

	

}
