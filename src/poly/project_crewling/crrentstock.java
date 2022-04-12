package poly.project_crewling;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crrentstock {

	public static void main(String[] args) throws Exception {
		// 투자자별 매매동향
		String url3 = "https://finance.naver.com/item/sise.nhn?code=252670";
		Document doc3 = null;
		doc3 = Jsoup.connect(url3).get();

		// 투자자별 매매동향
		Elements stock_deal = doc3.select("table.type2");
		//System.out.println("stock_deal:"+stock_deal);

		// 투자자별 매매동향
		String crrentstock = stock_deal.select("tbody").select("tr").eq(0).select("td").eq(0).select("strong").text();

		System.out.println(crrentstock);

	}

}
