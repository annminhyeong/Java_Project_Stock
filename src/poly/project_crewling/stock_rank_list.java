package poly.project_crewling;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stock_rank_list {
	public static void main(String[] args) throws Exception {

		// 메인페이지
		String url = "https://finance.naver.com/sise/lastsearch2.nhn";

		Document doc = null;
		doc = Jsoup.connect(url).get();

		//검색어 인기검색어
		Elements popular = doc.select("table.type_5");
		//System.out.println(popular);

		// 코스피 인기종목
		Iterator<Element> popular_List = popular.select("tbody tr").iterator();

		// 코스피 인기종목
		System.out.println("-인기종목-");
		while (popular_List.hasNext()) {
			Element info = popular_List.next();
			String rank = info.select("td").eq(0).text();
			String company_name = info.select("td").eq(1).text();
			String searchstate = info.select("td").eq(2).text();
			
			if(!rank.equals("")) {
				System.out.println("순위: " + rank);
				System.out.println("종목명: " + company_name);
				System.out.println("검색비율: " + searchstate);
			}

		}

	}

}
