package poly.project_crewling;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stock_news_crewling {

	public static void main(String[] args) throws Exception {
		
		// 종목 뉴스
		String url = "https://finance.naver.com/item/news_news.nhn?code=207940&sm=title_entity_id.basic&clusterId=&page=";
		Document newsDoc = null;
		
		for(int i = 1; i<=10;i++) {
			
			url = url+Integer.toString(i);
			newsDoc = Jsoup.connect(url).get();
			
			Elements element = newsDoc.select("div.tb_cont > table.type5");
			
			Iterator<Element> newstitle = element.select("tbody tr td.title").iterator();
			Iterator<Element> newsinfo = element.select("tbody tr td.info").iterator();
			Iterator<Element> newsdate = element.select("tbody tr td.date").iterator();
			
			while (newstitle.hasNext()) {
				Element titleInfo = newstitle.next();
				Element info = newsinfo.next();
				Element date = newsdate.next();
				
				String link = titleInfo.select("a").attr("href");
				String news_title = titleInfo.select("a").text();
				String writer = info.text();
				String time = date.text();
				
				System.out.println(i+"번 페이지");
				System.out.println("link : "+link);
				System.out.println("news_title : "+news_title);
				System.out.println("writer : "+writer);
				System.out.println("time : "+time);
				
				
				//종목 뉴스 내용
				String url1 = "https://finance.naver.com"+link;
				System.out.println();

				Document doc3 = null;
				doc3 = Jsoup.connect(url1).get();
				
				Elements NewContent_element = doc3.select("div#news_read");
				// System.out.println("stock_deal:"+stock_deal);

				//종목 뉴스 내용
				String news_contents = NewContent_element.text();
				
				System.out.println(news_contents);
				
				
				
			}
			
		}
	}
}
