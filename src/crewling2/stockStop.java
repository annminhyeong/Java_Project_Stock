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

public class stockStop {
	public static void main(String[] args) throws Exception {

		String url = "https://finance.naver.com/sise/trading_halt.nhn";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("tbody");
		//System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element stopstock = eList.next();
			CrewlingDTO pDTO = new CrewlingDTO();
			String stop_num = stopstock.select("td").eq(0).text();
			String stop_name = stopstock.select("td").eq(1).select("a").text();
			String stop_date = stopstock.select("td").eq(2).text();
			String stop_cause = stopstock.select("td").eq(3).text();

			if (!stop_num.equals("")) {
				System.out.println("----------------------");
				System.out.println("거래정지 순번: " + stop_num);
				System.out.println("거래정지 종목: " + stop_name);
				System.out.println("거래정지 날짜: " + stop_date);
				System.out.println("거래정지 이유: " + stop_cause);
				
				pDTO.setStop_num(stop_num);
				pDTO.setStop_name(stop_name);
				pDTO.setStop_date(stop_date);
				pDTO.setStop_cause(stop_cause);
			}

		}

	}
}