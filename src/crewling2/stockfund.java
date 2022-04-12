package crewling2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import poly.dto.NewsDTO;

public class stockfund {

	public static void main(String[] args) throws Exception {

		String url = "https://finance.naver.com/fund/fundTypeEarningRate.nhn";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("table.tbl_fund");
		
		Iterator<Element> eList = element.select("tr").iterator();
		List<NewsDTO> pList = new ArrayList<NewsDTO>();

		while (eList.hasNext()) {
			Element newsInfo = eList.next();
			NewsDTO pDTO = new NewsDTO();
			String news_link = newsInfo.select("td").eq(1).select("a").text();
			String news_title = newsInfo.select("td").eq(2).text();
			String news_summary = newsInfo.select("td").eq(3).text();
			String news_reporter = newsInfo.select("td").eq(4).text();
			String news_reporter1 = newsInfo.select("td").eq(5).text();
			String news_reporter2 = newsInfo.select("td").eq(6).text();
			String news_reporter3 = newsInfo.select("td").eq(7).text();

			System.out.println("news_link:" + news_link);
			System.out.println("news_title:" + news_title);
			System.out.println("news_summary:" + news_summary);
			System.out.println("news_reporter=" + news_reporter);
			System.out.println("news_reporter1=" + news_reporter1);
			System.out.println("news_reporter2=" + news_reporter2);
			System.out.println("news_reporter3=" + news_reporter3);

		}

	}

}
