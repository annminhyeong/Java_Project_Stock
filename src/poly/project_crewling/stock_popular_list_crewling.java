package poly.project_crewling;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stock_popular_list_crewling {

	public static void main(String[] args) throws Exception {
		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=207940";

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 코스피 인기종목
		Elements kospi_element = doc.select("div#fav_kospi");
		// System.out.println("test:"+kospi_element);
		// 코스닥 인기종목
		Elements kosdaq_element = doc.select("div#fav_kosdaq");

		// 코스피 인기종목
		Iterator<Element> kospi_List = kospi_element.select("table tbody tr").iterator();

		// 코스닥 인기종목
		Iterator<Element> kosdaq_List = kosdaq_element.select("table tbody tr").iterator();

		// 코스피 인기종목
		System.out.println("-코스피 인기종목-");
		while (kospi_List.hasNext()) {
			Element info = kospi_List.next();
			String num = info.select("th > a").text();

			System.out.println("코스피 종목 : " + num);
		}

		// 코스닥 인기종목
		System.out.println("-코스닥 인기종목-");
		while (kosdaq_List.hasNext()) {
			Element info = kosdaq_List.next();
			String num = info.select("th a").text();

			System.out.println("코스닥 종목 :" + num);
		}
	}
}
