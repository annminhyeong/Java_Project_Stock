package poly.project_crewling;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class stock_deal_crewling {

	public static void main(String[] args) throws Exception {

		// 투자자별 매매동향
		String url3 = "https://finance.naver.com/item/frgn.nhn?code=207940";

		Document doc3 = null;
		doc3 = Jsoup.connect(url3).get();

		// 투자자별 매매동향
		Elements stock_deal = doc3.select("table.type2");
		// System.out.println("stock_deal:"+stock_deal);

		// 투자자별 매매동향
		Iterator<Element> stock_deal_List = stock_deal.select("tbody tr").iterator();

		// 투자자별 매매동향
		System.out.println("-투자자별 매매동향-");
		while (stock_deal_List.hasNext()) {
			Element info = stock_deal_List.next();
			String stock_deal_date = info.select("td").eq(0).select("span").text();
			String stock_deal_data = info.select("td").eq(4).select("span").text();
			if(!stock_deal_data.equals("")) {
				String stock_deal_institution  = info.select("td").eq(5).select("span").text();
				String stock_deal_foreign = info.select("td").eq(6).select("span").text();

				System.out.println("날짜 : " + stock_deal_date);
				System.out.println("거래량 : " + stock_deal_data);
				System.out.println("기관 순매매량 : " + stock_deal_institution);
				System.out.println("외국인 순매매량 : " + stock_deal_foreign);
			}

		}

	}

}
