package poly.project_crewling;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class kosdaqgrap_crewling {
	public static void main(String[] args) throws Exception {

		// 투자자별 매매동향
		String url3 = "https://finance.naver.com/sise/sise_index_day.nhn?code=KOSDAQ&page=1";
		Document doc3 = null;
		doc3 = Jsoup.connect(url3).get();

		// 투자자별 매매동향
		Elements stock_deal = doc3.select("table.type_1");
		//System.out.println("stock_deal:"+stock_deal);

		// 투자자별 매매동향
		Iterator<Element> stock_deal_List = stock_deal.select("tbody tr").iterator();

		// 투자자별 매매동향
		System.out.println("-투자자별 매매동향-");
		while (stock_deal_List.hasNext()) {
			Element info = stock_deal_List.next();
			String date = info.select("td").eq(0).text();
			String data = info.select("td").eq(1).text();


			System.out.println("날짜 : " + date);
			System.out.println("데이터:" + data);
		}

	}
}
