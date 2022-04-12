package poly.project_crewling;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stock_talk_crewling {

	public static void main(String[] args) throws Exception {

		// 종목 토론실
		String url2 = "https://finance.naver.com/item/board.nhn?code=207940";

		Document doc2 = null;
		doc2 = Jsoup.connect(url2).get();

		// 종목 토론실
		Elements stock_talk = doc2.select("table.type2");
		//System.out.println("stock_talk:" + stock_talk);

		// 종목 토론실
		Iterator<Element> stock_talk_List = stock_talk.select("tbody tr").iterator();

		// 종목 토론실
		System.out.println("-종목 토론실-");
		while (stock_talk_List.hasNext()) {
			Element info = stock_talk_List.next();
			String num = info.select("td").eq(0).select("span").text();
			String link = info.select("td").eq(1).select("a").attr("href");
			String num3 = info.select("td").eq(1).select("a").text();
			String num4 = info.select("td").eq(2).text();

			System.out.println("날짜 : " + num);
			System.out.println("링크 : " + link);
			System.out.println("제목 : " + num3);
			System.out.println("아이디 : " + num4);
			
			
			
			//종목 뉴스 내용
			String url1 = "https://finance.naver.com"+link;

			Document doc3 = null;
			doc3 = Jsoup.connect(url1).get();
			
			Elements talkContent_element = doc3.select("div.view_se");
			// System.out.println("stock_deal:"+stock_deal);

			// 종목 뉴스 내용
			String talk_contents = talkContent_element.text();
			
			System.out.println("내용: "+talk_contents);
		}

	}

}
