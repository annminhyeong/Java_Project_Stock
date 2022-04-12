package crewling2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import poly.dto.CrewlingDTO;
import poly.dto.NewsDTO;

public class stockOil {
	public static void main(String[] args) throws Exception {

		String url = "https://finance.naver.com/marketindex/?tabSel=gold#tab_section";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("table.tbl_exchange");
		//System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element stopstock = eList.next();
			CrewlingDTO pDTO = new CrewlingDTO();
			
			String oil_name = stopstock.select("td").eq(0).select("a").text();
			String oil_unit = stopstock.select("td").eq(1).text();
			String oil_present_price = stopstock.select("td").eq(2).text();
			String oil_price_change = stopstock.select("td").eq(4).text();
			String oil_standard_date = stopstock.select("td").eq(5).text();
			
			oil_present_price =oil_present_price.replaceAll(",", "");

			if (!oil_name.equals("")) {
				System.out.println("----------------------");
				System.out.println("유가 이름 : " + oil_name);
				System.out.println("단위: " + oil_unit);
				System.out.println("현재가: " + oil_present_price);
				System.out.println("등락율: " + oil_price_change);
				System.out.println("기준일: " + oil_standard_date);
				
				pDTO.setOil_name(oil_name);
				pDTO.setOil_unit(oil_unit);
				pDTO.setOil_present_price(oil_present_price);
				pDTO.setOil_price_change(oil_price_change);
				pDTO.setOil_standard_date(oil_standard_date);
				
			}

		}

	}
}