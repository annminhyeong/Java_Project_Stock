package poly.project_crewling;

import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stock_relate_company_crewling {

	public static void main(String[] args) throws Exception {

		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=207940";

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 동일 업종 비교
		// class =section trade_compare 에서 section은 클레스 이름이 아님
		Elements same_company_element = doc.select("div.trade_compare");

		// 동일 업종 데이터
		Elements same_data_element = doc.select("div.trade_compare");

		// 동일 업종 이름
		Iterator<Element> same_company_List = same_company_element.select("table thead tr th").iterator();

		// 동일 업종 데이터
		Iterator<Element> same_data_List = same_data_element.select("table tbody tr").eq(2).iterator();

			while (same_data_List.hasNext()) {
				Element info = same_data_List.next();
					
				for(int i=0; i<5; i++) {
					String num1 = info.select("td").eq(i).text();// 현재가
					System.out.println("현재가:" + num1);
				}
			}

		

		// 동일 업종 비교
		System.out.println("-동일 업종 비교-");
		while (same_company_List.hasNext()) {
			Element info = same_company_List.next();
			String num = info.select("a").text();

			System.out.println("동일 업종 :" + num);
		}

	}

}
