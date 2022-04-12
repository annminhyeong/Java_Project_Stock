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

public class stockTheme {
	public static void main(String[] args) throws Exception {

		String url = "https://finance.naver.com/sise/theme.nhn";

		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("table.theme");
//System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element stopstock = eList.next();
			CrewlingDTO pDTO = new CrewlingDTO();
			String theme_num = stopstock.select("td").eq(0).text();
			String theme_name = stopstock.select("td").eq(1).select("span").text();
			String theme_date = stopstock.select("td").eq(2).text();
			String theme_ju1= stopstock.select("td").eq(6).text();
			String theme_ju2 = stopstock.select("td").eq(7).text();

			if (!theme_name.equals("")) {
				System.out.println("----------------------");
				System.out.println("테마명: " + theme_num);
				System.out.println("전일대비: " + theme_name);
				System.out.println("등락율(3일 평균): " + theme_date);
				System.out.println("주도주 1: " + theme_ju1);
				System.out.println("주도주 2 : " + theme_ju2);
				
				pDTO.setTheme_num(theme_num);
				pDTO.setTheme_name(theme_name);
				pDTO.setTheme_date(theme_date);
				pDTO.setTheme_ju1(theme_ju1);
				pDTO.setTheme_ju2(theme_ju2);
			}

		}

	}
}